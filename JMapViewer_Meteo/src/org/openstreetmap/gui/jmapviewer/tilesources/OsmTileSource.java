package org.openstreetmap.gui.jmapviewer.tilesources;



/**
 * OSM Tile source.
 */
public class OsmTileSource {

    /**
     * The default "Mapnik" OSM tile source URL
     */
	/**
     * The default "Mapnik" OSM tile source URL
     */
    public static final String MAP_MAPNIK = "http://tile.openstreetmap.org";
    public static final String MAP_OSMA = "http://tah.openstreetmap.org/Tiles";
    public static final String MAP_SEAMAP = "http://tiles.openseamap.org/seamark";
    public static final String MAP_THUN = "http://tile.thunderforest.com/landscape/";
    public static final String MAP_EMPTY = "";
    

    /**
     * The default "Mapnik" OSM tile source.
     */
    public static class Mapnik extends AbstractOsmTileSource {
        
        /**
         * Constructs a new {@code "Mapnik"} tile source.
         */
        public Mapnik() {
            super("Mapnik", MAP_MAPNIK);
        }

        public TileUpdate getTileUpdate() {
            return TileUpdate.IfNoneMatch;
        }
    }

    public static class SeaMap extends AbstractOsmTileSource {
        public SeaMap() {
            super("OpenSeaMap", MAP_SEAMAP);
        }

        public TileUpdate getTileUpdate() {
            return TileUpdate.IfNoneMatch;
        }

    }
    
    public static class EmptyMap extends AbstractOsmTileSource {
        public EmptyMap() {
            super("(Empty Source)", MAP_EMPTY);
        }

        public TileUpdate getTileUpdate() {
            return TileUpdate.IfNoneMatch;
        }

    }
    

    
    public static class Thun extends AbstractOsmTileSource {
        public Thun() {
            super("ThunderForest", MAP_THUN);
        }

        public TileUpdate getTileUpdate() {
            return TileUpdate.IfNoneMatch;
        }

    }
    
    /**
     * The "Cycle Map" OSM tile source.
     */
    public static class CycleMap extends AbstractOsmTileSource {

        private static final String PATTERN = "http://%s.tile.opencyclemap.org/cycle";

        private static final String[] SERVER = { "a", "b", "c" };

        private int SERVER_NUM = 0;

        /**
         * Constructs a new {@code CycleMap} tile source.
         */
        public CycleMap() {
            super("OSM Cycle Map", PATTERN);
        }

        @Override
        public String getBaseUrl() {
            String url = String.format(this.baseUrl, new Object[] { SERVER[SERVER_NUM] });
            SERVER_NUM = (SERVER_NUM + 1) % SERVER.length;
            return url;
        }

        @Override
        public int getMaxZoom() {
            return 18;
        }

        public TileUpdate getTileUpdate() {
            return TileUpdate.LastModified;
        }
    }
}

