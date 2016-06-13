package others;

public class Polygon {

	private Position[] points;
	private Position center;
	private double rotation;

	/**
	 * Put the points in order or expect a crash
	 * 
	 * @param points
	 * @param center
	 */
	public Polygon(Position[] points, Position center) {
		setPositions(points, center);
		rotation = 0;
	}
	
	private void setPositions(Position[] points, Position center) {
		this.points = new Position[points.length];
		this.center = new Position(center.getX(), center.getY());
		for(int i = 0; i < points.length; i++) {
			this.points[i] = new Position(points[i].getX(), points[i].getY());
		}
	}

	/**
	 * Rotates the shape theta rad in the positive direction around the center
	 * of the shape
	 * 
	 * @param theta
	 */
	public void rotate(double theta) {
		rotation += theta;
		if(rotation / (2 * Math.PI) < 1)
			rotation = rotation - 2 * Math.PI;
		Position[] tempPoints = new Position[points.length];
		double alpha;
		for (int i = 0; i < points.length; i++) {
			if(points[i].getY() < center.getY())
				alpha = Math.acos((points[i].getX() - center.getX()) / Math.sqrt(Math.pow(points[i].getX() - center.getX(), 2)
						+ Math.pow(points[i].getY() - center.getY(), 2)));
			else
				alpha = -(Math.acos((points[i].getX() - center.getX()) / Math.sqrt(Math.pow(points[i].getX() - center.getX(), 2)
						+ Math.pow(points[i].getY() - center.getY(), 2))));
			tempPoints[i] = new Position(
					center.getX() + Math.cos(theta + alpha) * Math.sqrt(Math.pow(points[i].getX() - center.getX(), 2)
							+ Math.pow(points[i].getY() - center.getY(), 2)),
					center.getY() + Math.sin(-(theta + alpha)) * Math.sqrt(Math.pow(points[i].getX() - center.getX(), 2)
							+ Math.pow(points[i].getY() - center.getY(), 2)));
		}
		points = tempPoints;
	}
	
	/**
	 * Returns the current rotation of the polygon in radians
	 * @return double theta
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * Returns true if two !!!CONVEX!!! shapes are intersecting (separating axis
	 * theorem)
	 * 
	 * @param s
	 * @return
	 */
	public boolean intersects(Polygon s) {
		Position[] temp = s.points;
		Position[] vectors = new Position[temp.length + points.length];
		Position[] normals = new Position[temp.length + points.length];

		for (int i = 0; i < temp.length; i++) {
			Position temp0, temp1;
			if (i == temp.length - 1) {
				temp0 = temp[i];
				temp1 = temp[0];
			} else {
				temp0 = temp[i];
				temp1 = temp[i + 1];
			}
			vectors[i] = new Position(temp1.getX() - temp0.getX(), temp1.getY() - temp0.getY());
			normals[i] = new Position(vectors[i].getY(), vectors[i].getX());
		}

		for (int i = 0; i < points.length; i++) {
			Position temp0, temp1;
			if (i == points.length - 1) {
				temp0 = points[i];
				temp1 = points[0];
			} else {
				temp0 = points[i];
				temp1 = points[i + 1];
			}
			vectors[temp.length + i] = new Position(temp1.getX() - temp0.getX(), temp1.getY() - temp0.getY());
			normals[temp.length + i] = new Position(vectors[temp.length + i].getY(), -vectors[temp.length + i].getX());
		}
		
		Position[] polyAPoints = new Position[temp.length];
		Position[] polyBPoints = new Position[points.length];
		for (int i = 0; i < normals.length; i++) {
			for (int j = 0; j < polyAPoints.length; j++) {
				double k = (normals[i].getY() * (-temp[j].getX()) + normals[i].getX() * temp[j].getY())
						/ (vectors[i].getX() * normals[i].getY() - vectors[i].getY() * normals[i].getX());
				polyAPoints[j] = new Position(temp[j].getX() + k * (vectors[i].getX()),
						temp[j].getY() + k * (vectors[i].getY()));
			}
			for (int j = 0; j < polyBPoints.length; j++) {
				double k = (normals[i].getY() * (-points[j].getX()) + normals[i].getX() * points[j].getY())
						/ (vectors[i].getX() * normals[i].getY() - vectors[i].getY() * normals[i].getX());
				polyBPoints[j] = new Position(points[j].getX() + k * (vectors[i].getX()),
						points[j].getY() + k * (vectors[i].getY()));
			}

			if (normals[i].getX() == 0) {
				double polyAMinY = Double.MAX_VALUE, polyAMaxY = Double.MIN_VALUE, polyBMinY = Double.MAX_VALUE,
						polyBMaxY = Double.MIN_VALUE;
				for (Position p : polyAPoints) {
					if (p.getY() < polyAMinY)
						polyAMinY = p.getY();
					if (p.getY() > polyAMaxY)
						polyAMaxY = p.getY();
				}
				for (Position p : polyBPoints) {
					if (p.getY() < polyBMinY)
						polyBMinY = p.getY();
					if (p.getY() > polyBMaxY)
						polyBMaxY = p.getY();
				}
				if (polyAMinY > polyBMaxY || polyAMaxY < polyBMinY)
					return false;
			} else {
				double polyAMinX = Double.MAX_VALUE, polyAMaxX = Double.MIN_VALUE, polyBMinX = Double.MAX_VALUE,
						polyBMaxX = Double.MIN_VALUE;
				for (Position p : polyAPoints) {
					if (p.getX() < polyAMinX)
						polyAMinX = p.getX();
					if (p.getX() > polyAMaxX)
						polyAMaxX = p.getX();
				}
				for (Position p : polyBPoints) {
					if (p.getX() < polyBMinX)
						polyBMinX = p.getX();
					if (p.getX() > polyBMaxX)
						polyBMaxX = p.getX();
				}
				if (polyAMinX > polyBMaxX || polyAMaxX < polyBMinX)
					return false;
			}
		}
		return true;
	}
	
	public Position[] getPoints() {
		return points;
	}
	
	public void move(double dX, double dY) {
		for(Position p : points) {
			p.setX(p.getX() + dX);
			p.setY(p.getY() + dY);
		}
		center.setX(center.getX() + dX);
		center.setY(center.getY() + dY);
	}
	
	public double[] getXPoints() {
		double[] xPoints = new double[points.length];
		for(int i = 0; i < points.length; i++) {
			xPoints[i] = points[i].getX();
		}
		return xPoints;
	}
	
	public double[] getYPoints() {
		double[] yPoints = new double[points.length];
		for(int i = 0; i < points.length; i++) {
			yPoints[i] = points[i].getY();
		}
		return yPoints;
	}
}
