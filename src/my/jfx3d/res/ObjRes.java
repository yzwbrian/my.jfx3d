package my.jfx3d.res;

import java.net.URISyntaxException;

public class ObjRes {

	private ObjRes() {}
	
	private static final ObjRes ObjRes = new ObjRes();
	public static final String RootPath = "/my/jfx3d/res/obj";
	public static String getObj(String name) throws URISyntaxException {
		return ObjRes.getClass().getResource(RootPath+"/"+name).toExternalForm();
	}
}
