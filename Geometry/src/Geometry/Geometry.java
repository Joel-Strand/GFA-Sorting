package Geometry;

public class Geometry implements IGeometry {
    // Geometric Object
    private double[][][] shape;
    // Dimensions
    private double magX;
    private double magY;
    private double magZ;

    public Geometry(String shape, double magX, double magY, double magZ) {

    }

    @Override
    public boolean append() {
        return false;
    }

    @Override
    public boolean resize() {
        return false;
    }

    @Override
    public double size() {
        return 0;
    }

    @Override
    public double sizeByCord() {
        return 0;
    }

    @Override
    public boolean splice() {
        return false;
    }

    @Override
    public Geometry rectangle() {
        return null;
    }

    @Override
    public Geometry triangle() {
        return null;
    }

    @Override
    public Geometry circle() {
        return null;
    }

    @Override
    public Geometry cube() {
        return null;
    }

    @Override
    public Geometry tetrahedron() {
        return null;
    }

    @Override
    public Geometry cylinder() {
        return null;
    }
}
