package entities;
import java.awt.geom.Arc2D.Double;

import javafx.scene.canvas.GraphicsContext;
import others.World;

public abstract class HostileEntity extends AnimateEntity{

	public HostileEntity(double speed, int x, int y, double width, double height) {
		super(speed, x, y, width, height);
	}


	@Override
	public abstract void paint(GraphicsContext gc);

	public abstract AnimateEntity checkArcIntersection(Double arc);
}
