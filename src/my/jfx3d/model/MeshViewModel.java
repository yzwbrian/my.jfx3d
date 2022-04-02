package my.jfx3d.model;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import my.jfx3d.base.Xform;
import my.jfx3d.view.MyTriangle;

public class MeshViewModel {
	private final MeshView meshView = new MeshView();
	private final TriangleMesh mesh = new TriangleMesh();
	
	public MeshViewModel() {}
	
	public void clear() {
		mesh.getPoints().clear();
		mesh.getTexCoords().clear();
		mesh.getFaces().clear();
	}
	
	public final MeshView generateMeshView(final Xform pointsXform, final Xform triangleXform) {
		clear();
		Map<Point, Integer> pointsMap = new HashMap<>();
		ObservableList<Node> nodes = triangleXform.getChildren();
		System.out.println("triangle-size:"+nodes.size());
		int n = -1;
		for(Node node: nodes) {
			MyTriangle mt = (MyTriangle) node;
			Point pa = mt.getA().getPoint(), pb = mt.getB().getPoint(), pc = mt.getC().getPoint();
			TriangleMesh mtMesh = (TriangleMesh) mt.getMesh();
			
			if(!pointsMap.containsKey(pa))
			{
				n++;
				pointsMap.put(pa, n);
				mesh.getPoints().addAll(
					(float) pa.getX(),
					(float) pa.getY(),
					(float) pa.getZ()
				);
			}
			
			if(!pointsMap.containsKey(pb))
			{
				n++;
				pointsMap.put(pb, n);
				mesh.getPoints().addAll(
					(float) pb.getX(),
					(float) pb.getY(),
					(float) pb.getZ()
				);
			}
			
			if(!pointsMap.containsKey(pc))
			{
				n++;
				pointsMap.put(pc, n);
				mesh.getPoints().addAll(
					(float) pc.getX(),
					(float) pc.getY(),
					(float) pc.getZ()
				);
			}
			
			int n1 = mtMesh.getFaces().get(0);
			int n2 = mtMesh.getFaces().get(2);
			int n3 = mtMesh.getFaces().get(4);
			if(n1==0 && n2==1 && n3 == 2)
				mesh.getFaces().addAll(
						pointsMap.get(pa), 0,
						pointsMap.get(pb), 0,
						pointsMap.get(pc), 0
				);
			else 
				mesh.getFaces().addAll(
						pointsMap.get(pc), 0,
						pointsMap.get(pb), 0,
						pointsMap.get(pa), 0
				);
		}
		mesh.getTexCoords().addAll(0,0);
		meshView.setMesh(mesh);
		meshView.setMaterial(new PhongMaterial(
				Color.CHARTREUSE));
		//meshView.setCullFace(CullFace.BACK);
		debug();
		return meshView;
	}
	
	public void debug() {
		System.out.println("point-size:"+mesh.getPoints().size());
		int n = mesh.getPoints().size();
		for(int i=0;i<n;i++) {
			if(i%6 == 0)
				System.out.println();
			else if(i%2 == 0)
				System.out.print("\t");
			System.out.print(mesh.getPoints().get(i)+", ");
		}
		System.out.println();
		
		System.out.println("face-size:"+mesh.getFaces().size());
		n = mesh.getFaces().size();
		for(int i=0;i<n;i++) {
			if(i%6 == 0)
				System.out.println();
			else if(i%2 == 0)
				System.out.print("  ");
			System.out.print(mesh.getFaces().get(i)+", ");
		}
	}
	
}
