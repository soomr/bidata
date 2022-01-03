import org.nocrala.tools.gis.data.esri.shapefile.ShapeFileReader;
import org.nocrala.tools.gis.data.esri.shapefile.ValidationPreferences;
import org.nocrala.tools.gis.data.esri.shapefile.exception.InvalidShapeFileException;
import org.nocrala.tools.gis.data.esri.shapefile.shape.AbstractShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.PointData;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PolygonShape;

import java.awt.geom.Path2D;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

public class DistrictDirectory {
    ArrayList<Path2D> pathList = new ArrayList<>();
    HashMap<Path2D, String> hash = new HashMap<>();

    public String posToDistrict(double x, double y) {
        for (Path2D p : pathList) {
            if (p.contains(x, y)) {
                return hash.get(p);
            }
        }
        return "-1";
    }

    public DistrictDirectory() {
        try {
            initialize();
        } catch (IOException | InvalidShapeFileException e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws IOException, InvalidShapeFileException {
        ArrayList<PolygonShape> polyShapeList = new ArrayList<>();
        FileInputStream is = new FileInputStream(
                "C:\\Users\\adama\\IdeaProjects\\big data\\src\\geo_export_2ff06a66-3049-4594-819a-946af1becdba.shp");
        ValidationPreferences prefs = new ValidationPreferences();
        prefs.setMaxNumberOfPointsPerShape(99000);
        ShapeFileReader r = new ShapeFileReader(is, prefs);
        File csvFile = new File("C:\\Users\\adama\\IdeaProjects\\big data\\src\\2ff06a66-3049-4594-819a-946af1becdba.csv");
        BufferedReader br = new BufferedReader(new FileReader(csvFile));

        String line;
        ArrayList<String> names = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] cols;
            cols = line.split(",");
            names.add(cols[0]);
        }

        AbstractShape s;
        while ((s = r.next()) != null) {
            PolygonShape aPolygon = (PolygonShape) s;
            polyShapeList.add(aPolygon);
        }

        for (PolygonShape a : polyShapeList) {
            PointData[] p = a.getPoints();
            Path2D path = new Path2D.Double();
            path.moveTo(p[0].getX(), p[0].getY());
            IntStream.range(1, p.length).forEach(i -> path.lineTo(p[i].getX(), p[i].getY()));
            pathList.add(path);
        }
        for (int i = 0; i < names.size() - 1; i++) {
            hash.put(pathList.get(i), names.get(i));
        }
        is.close();
    }
}