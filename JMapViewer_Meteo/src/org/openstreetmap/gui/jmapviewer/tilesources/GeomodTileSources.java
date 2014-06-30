package org.openstreetmap.gui.jmapviewer.tilesources;


public class GeomodTileSources {

	public static final String GEOMOD_BASE = "http://marine.wms.geomod.fr/geowebcache/service/tms/1.0.0/base_DAY@EPSG%3A900913@png";
	public static final String GEOMOD_HYDROGRAPHY = "http://marine.wms.geomod.fr/geowebcache/service/tms/1.0.0/hydrography_DAY@EPSG%3A900913@png";
	public static final String GEOMOD_SOUNDINGS = "http://marine.wms.geomod.fr/geowebcache/service/tms/1.0.0/soundings_DAY@EPSG%3A900913@png";
	public static final String GEOMOD_DANGERS = "http://marine.wms.geomod.fr/geowebcache/service/tms/1.0.0/dangers_DAY@EPSG%3A900913@png";
	public static final String GEOMOD_RESTRICTIONS = "http://marine.wms.geomod.fr/geowebcache/service/tms/1.0.0/restrictions_DAY@EPSG%3A900913@png";
	public static final String GEOMOD_TOPOGRAPHY = "http://marine.wms.geomod.fr/geowebcache/service/tms/1.0.0/topo_DAY@EPSG%3A900913@png";
	public static final String GEOMOD_AIDS_TO_NAVIGATION = "http://marine.wms.geomod.fr/geowebcache/service/tms/1.0.0/aton_DAY@EPSG%3A900913@png";
	public static final String GEOMOD_METADATA = "http://marine.wms.geomod.fr/geowebcache/service/tms/1.0.0/tides@EPSG:900913@png";
	public static final String GEOMOD_COVERAGES = "http://marine.wms.geomod.fr/geowebcache/service/tms/1.0.0/coverages@EPSG:900913@png";
	
	public static class GBase extends AbstractGeomodTileSource {

		public GBase() {
			super("GeomodBase", GEOMOD_BASE);
		}

		public TileUpdate getTileUpdate() {
			return TileUpdate.IfNoneMatch;
		}
	}
	
	public static class GHydro extends AbstractGeomodTileSource {

		public GHydro() {
			super("GeomodHydrography", GEOMOD_HYDROGRAPHY);
		}

		public TileUpdate getTileUpdate() {
			return TileUpdate.IfNoneMatch;
		}
	}
	
	public static class GSoundings extends AbstractGeomodTileSource {

		public GSoundings() {
			super("GeomodSounding", GEOMOD_SOUNDINGS);
		}

		public TileUpdate getTileUpdate() {
			return TileUpdate.IfNoneMatch;
		}
	}
	
	public static class GDangers extends AbstractGeomodTileSource {

		public GDangers() {
			super("GeomodDangers", GEOMOD_DANGERS);
		}

		public TileUpdate getTileUpdate() {
			return TileUpdate.IfNoneMatch;
		}
	}
	
	public static class GRestriction extends AbstractGeomodTileSource {

		public GRestriction() {
			super("GeomodRestriction", GEOMOD_RESTRICTIONS);
		}

		public TileUpdate getTileUpdate() {
			return TileUpdate.IfNoneMatch;
		}
	}
	
	public static class GTopography extends AbstractGeomodTileSource {

		public GTopography() {
			super("GeomodTopography", GEOMOD_TOPOGRAPHY);
		}

		public TileUpdate getTileUpdate() {
			return TileUpdate.IfNoneMatch;
		}
	}
	
	public static class GAidsToNavigation extends AbstractGeomodTileSource {

		public GAidsToNavigation() {
			super("GeomodAidsToNavigation", GEOMOD_AIDS_TO_NAVIGATION);
		}

		public TileUpdate getTileUpdate() {
			return TileUpdate.IfNoneMatch;
		}
	}
	
	public static class GMetadata extends AbstractGeomodTileSource {

		public GMetadata() {
			super("GeomodMetadata", GEOMOD_METADATA);
		}

		public TileUpdate getTileUpdate() {
			return TileUpdate.IfNoneMatch;
		}
	}
	
	public static class GCoverage extends AbstractGeomodTileSource {

		public GCoverage() {
			super("GeomodCoverage", GEOMOD_COVERAGES);
		}

		public TileUpdate getTileUpdate() {
			return TileUpdate.IfNoneMatch;
		}
	}
	
}
