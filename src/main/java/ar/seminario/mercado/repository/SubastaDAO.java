package ar.seminario.mercado.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jredis.JRedis;
import org.jredis.RedisException;
import org.jredis.ri.alphazero.support.DefaultCodec;

import ar.seminario.mercado.Subasta;
import ar.seminario.mercado.dao.exceptions.NotFoundException;
public class SubastaDAO {

	private JRedis dbClient;

	public SubastaDAO(JRedis  dbClient) {
		this.dbClient = dbClient;
	}

	public Subasta store(Subasta nuevaSubasta) {
		nuevaSubasta.setId(buildNewId());
		
		try {
			this.dbClient.mset(getMainHash(nuevaSubasta));
			this.dbClient.rpush("subastas:keys", nuevaSubasta.getId().getId());
		} catch (RedisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return nuevaSubasta;
	}

	public Subasta find(ObjectId id) throws NotFoundException {
		String basicKey = getBasicKey(id);
		String existence = basicKey.concat(":_");
		String[] keys = dataKeys(basicKey);
		List<byte[]> data;
		try {
			data = this.dbClient.mget(existence, keys);
			if (data.get(0) != null) {
				Boolean exists = DefaultCodec.decode(data.get(0));
				if (exists) {
					Subasta s = new Subasta();
					s.setId(id);
					return s;
				}
			}
			throw new NotFoundException(Subasta.class, id);
		} catch (RedisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NotFoundException(Subasta.class, id);
		}
	}

	private String[] dataKeys(String basicKey) {
		String[] s = {};
		return s;
	}

	public Collection<Subasta> getAll() {
		List<byte[]> keys;
		try {
			keys = this.dbClient.lrange("subastas:keys", 0, -1);
			List<ObjectId> oIds = idsFromKeys(keys);
			String[] hashKeys = allKeys(oIds);
			List<byte[]> data = this.dbClient.mget("subastas:seq", hashKeys);
			return hydrateMultiple(oIds, data);
		} catch (RedisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected List<ObjectId> idsFromKeys(List<byte[]> keys) {
		if (keys == null) return new ArrayList<ObjectId>();
		
		List<ObjectId> ids = new ArrayList<ObjectId>();
		for(byte[] key : keys) {
			ids.add(new ObjectId(DefaultCodec.toLong(key)));
		}
		return ids;
	}

	protected Collection<Subasta> hydrateMultiple(List<ObjectId> ids, List<byte[]> data) {
		int currentIndex = 0;
		int dataKeys = 0;
		Collection<Subasta> subastas = new ArrayList<Subasta>();
		for(ObjectId id : ids) {
			List<byte[]> attributes = data.subList(currentIndex, currentIndex + dataKeys + 1);
			if (attributes.get(0) != null) { // && DefaultCodec.decode(attributes.get(0))) {
				subastas.add(hydrateOne(id, attributes));
			}
			currentIndex += dataKeys + 1;
		}
		return subastas;
	}

	private Subasta hydrateOne(ObjectId id, List<byte[]> attributes) {
		Subasta s = new Subasta();
		s.setId(id);
		return s;
	}

	protected String[] allKeys(List<ObjectId> oIds) {
		Collection<String> allKeys = new ArrayList<String>();
		for(ObjectId id : oIds) {
			String basicKey = getBasicKey(id);
			allKeys.add(basicKey.concat(":_"));
			Collections.addAll(allKeys, dataKeys(basicKey));
		}
		String[] arrKeys = new String[allKeys.size()];
		return allKeys.toArray(arrKeys);
	}

	public void remove(Subasta nuevaSubasta) {
		try {
			this.dbClient.lrem("subastas:keys", nuevaSubasta.getId().getId(), 1);
			String existence = getBasicKey(nuevaSubasta.getId()).concat(":_");
			this.dbClient.set(existence, false);
		} catch (RedisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected ObjectId buildNewId() {
		try {
			return new ObjectId(this.dbClient.incr("subastas:seq"));
		} catch (RedisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	protected Map<String, byte[]> getMainHash(Subasta nuevaSubasta) {
		Map<String, byte[]> mh = new HashMap<String, byte[]>();
		String basicKey = getBasicKey(nuevaSubasta);
		mh.put(basicKey.concat(":_"), DefaultCodec.encode(true));
		return mh ;
	}
	

	protected String getBasicKey(Subasta nuevaSubasta) {
		return getBasicKey(nuevaSubasta.getId());
	}

	protected String getBasicKey(ObjectId id) {
		return getBasicKey(id.getId());
	}
	
	protected String getBasicKey(Long l) {
		return "subastas:".concat(l.toString());
	}

}
