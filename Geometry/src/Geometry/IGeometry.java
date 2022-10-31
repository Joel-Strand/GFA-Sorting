package Geometry;

public interface IGeometry {
    boolean append();
    boolean resize();
    double size();
    double sizeByCord();
    boolean splice();

    Geometry rectangle();
    Geometry triangle();
    Geometry circle();

    Geometry cube();
    Geometry tetrahedron();
    Geometry cylinder();

}
