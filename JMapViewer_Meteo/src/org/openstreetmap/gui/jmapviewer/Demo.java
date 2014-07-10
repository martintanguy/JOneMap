// License: GPL. For details, see Readme.txt file.
package org.openstreetmap.gui.jmapviewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sourceforge.jgrib.examples.MeteoCoordinate;
import net.sourceforge.jgrib.examples.New_Test;

import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.openstreetmap.gui.jmapviewer.interfaces.TileLoader;
import org.openstreetmap.gui.jmapviewer.interfaces.TileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.BingAerialTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.GeomodTileSources;
//import org.openstreetmap.gui.jmapviewer.tilesources.GeomodTileSources;
import org.openstreetmap.gui.jmapviewer.tilesources.MapQuestOpenAerialTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.MapQuestOsmTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;
import org.openstreetmap.gui.jmapviewer.Tile;

import java.awt.Font;
//import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource.EmptyMap;



/**
 * Demonstrates the usage of {@link JMapViewer}
 *
 * @author Jan Peter Stotz
 *
 */
public class Demo extends JFrame implements JMapViewerEventListener  {

    private static final long serialVersionUID = 1L;

    private JMapViewerTree treeMap = null;

    private JLabel zoomLabel=null;
    private JLabel zoomValue=null;
    private JLabel mperpLabelName=null;
    private JLabel mperpLabelValue = null;
    
    
	private String listTileSourceNames;
	public float valeur_slide = 0f;
	public static BufferedImage GREEN;
    int i;

	private JSlider slider_2;

	private JTextArea textMeteo;


    

    /**
     * Constructs the {@code Demo}.
     */
    public Demo() {
        super("J-OneMap Demo");
        setSize(1000, 1000);

        treeMap = new JMapViewerTree("Zones");
        
        treeMap.getViewer().setBackground(new Color(255,255,255));
        treeMap.getViewer().zoomSlider.setOrientation(SwingConstants.VERTICAL);
     
        treeMap.getViewer().zoomInButton.setLocation(366, 72);
        treeMap.getViewer().zoomInButton.setLocation(277, 114);
        treeMap.getViewer().zoomSlider.setLocation(266, 46);
        treeMap.getViewer().zoomSlider.setLocation(280, 23);
        treeMap.getViewer().zoomSlider.setLocation(10, 0);
        treeMap.getViewer().zoomSlider.setEnabled(true);
        treeMap.getViewer().zoomSlider.setLocation(10, 20);
        treeMap.getViewer().zoomSlider.setLocation(103, 23);
        treeMap.getViewer().zoomOutButton.setLocation(149, 172);
        treeMap.getViewer().zoomOutButton.setLocation(301, 155);
        treeMap.getViewer().zoomOutButton.setLocation(500, 155);
        treeMap.getViewer().zoomSlider.setLocation(547, 131);
        treeMap.getViewer().zoomSlider.setLocation(88, 98);
        treeMap.getViewer().zoomOutButton.setLocation(104, 243);
        treeMap.getViewer().zoomInButton.setLocation(82, 243);
        treeMap.getViewer().zoomSlider.setLocation(1054, 74);
        treeMap.getViewer().zoomOutButton.setLocation(1070, 219);
        treeMap.getViewer().zoomInButton.setLocation(1048, 219);
        treeMap.getViewer().zoomSlider.setLocation(541, 31);
        treeMap.getViewer().zoomOutButton.setLocation(557, 176);
        treeMap.getViewer().zoomInButton.setLocation(535, 176);
        treeMap.getViewer().zoomInButton.setLocation(247, 155);
        treeMap.getViewer().zoomSlider.setLocation(253, 10);
        treeMap.getViewer().zoomOutButton.setLocation(269, 155);
        treeMap.getViewer().zoomInButton.setLocation(31, 155);
        treeMap.getViewer().zoomSlider.setLocation(37, 10);
        treeMap.getViewer().zoomOutButton.setLocation(53, 155);
        treeMap.getViewer().zoomInButton.setLocation(31, 155);
        treeMap.getViewer().zoomSlider.setLocation(37, 10);
        treeMap.getViewer().zoomOutButton.setLocation(53, 155);
        treeMap.getViewer().zoomInButton.setLocation(1840, 155);
        treeMap.getViewer().zoomSlider.setLocation(1850, 10);
        treeMap.getViewer().zoomOutButton.setLocation(1870, 155);
        
        // Listen to the map viewer for user operations so components will
        // recieve events and update
        map().addJMVListener(this);

        // final JMapViewer map = new JMapViewer(new MemoryTileCache(),4);
        // map.setTileLoader(new OsmFileCacheTileLoader(map));
        // new DefaultMapController(map);
        
        i=0;

        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JPanel panel = new JPanel();
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        panelBottom.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        JPanel helpPanel = new JPanel();
        
        map().setBounds(getBounds());

       

        mperpLabelName=new JLabel("Meters/Pixels: ");
        mperpLabelValue=new JLabel(String.format("%s",map().getMeterPerPixel()));

        zoomLabel=new JLabel("Zoom: ");
        zoomValue=new JLabel(String.format("%s", map().getZoom()));

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(helpPanel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout());
        panel.add(panelTop, BorderLayout.NORTH);
        panel.add(panelBottom, BorderLayout.SOUTH);
        JLabel helpLabel = new JLabel("Use right mouse button to move,\n "
                + "left double click or mouse wheel to zoom.");
        helpPanel.add(helpLabel);
        JButton button = new JButton("setDisplayToFitMapMarkers");
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                map().setDisplayToFitMapMarkers();
            }
        });
        addList();
        
  
        
        JComboBox tileLoaderSelector;
        try {
            tileLoaderSelector = new JComboBox(new TileLoader[] { new OsmFileCacheTileLoader(map()),
                    new OsmTileLoader(map()) });
        } catch (IOException e) {
            tileLoaderSelector = new JComboBox(new TileLoader[] { new OsmTileLoader(map()) });
        }
        tileLoaderSelector.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                map().setTileLoader((TileLoader) e.getItem());
            }
        });
        map().setTileLoader((TileLoader) tileLoaderSelector.getSelectedItem());;
        
        	
//        Coordinate topLeft = map().getPosition(0, 0);
//        Coordinate bottomRight = map().getPosition(map().getWidth(), map().getHeight());

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(UIManager.getBorder("DesktopIcon.border"));
        panel_2.setBackground(new Color(250, 235, 215));
        panel_2.setBounds(1835, 10, 59, 175);
        
        treeMap.getViewer().add(panel_2);
        panelTop.add(tileLoaderSelector);
        final JCheckBox showMapMarker = new JCheckBox("Map markers visible");
        showMapMarker.setSelected(map().getMapMarkersVisible());
        showMapMarker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                map().setMapMarkerVisible(showMapMarker.isSelected());
            }
        });
        panelBottom.add(showMapMarker);
        ///
        final JCheckBox showTreeLayers = new JCheckBox("Tree Layers visible");
        showTreeLayers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                treeMap.setTreeVisible(showTreeLayers.isSelected());
            }
        });
        panelBottom.add(showTreeLayers);
        ///
        final JCheckBox showToolTip = new JCheckBox("ToolTip visible");
        showToolTip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                map().setToolTipText(null);
            }
        });
        panelBottom.add(showToolTip);
        ///
        final JCheckBox showTileGrid = new JCheckBox("Tile grid visible");
        showTileGrid.setSelected(map().isTileGridVisible());
        showTileGrid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                map().setTileGridVisible(showTileGrid.isSelected());
                
            }
        });
        panelBottom.add(showTileGrid);
        final JCheckBox showZoomControls = new JCheckBox("Show zoom controls");
        showZoomControls.setSelected(map().getZoomContolsVisible());
        showZoomControls.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                map().setZoomContolsVisible(showZoomControls.isSelected());
            }
        });
        panelBottom.add(showZoomControls);
        final JCheckBox scrollWrapEnabled = new JCheckBox("Scrollwrap enabled");
        scrollWrapEnabled.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                map().setScrollWrapEnabled(scrollWrapEnabled.isSelected());
            }
        });
        panelBottom.add(scrollWrapEnabled);
        panelBottom.add(button);

        panelTop.add(zoomLabel);
        panelTop.add(zoomValue);
        panelTop.add(mperpLabelName);
        panelTop.add(mperpLabelValue);
        
        final JTextArea txtrTest = new JTextArea();
        txtrTest.setForeground(new Color(0, 0, 0));
        txtrTest.setBackground(new Color(255, 255, 204));
        txtrTest.setEditable(true);
        txtrTest.setRows(10);
        txtrTest.setText(listTileSourceNames);
        

        
        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.WEST);
        panel_1.setBorder(UIManager.getBorder("CheckBox.border"));
        
        JLabel lblNewLabel = new JLabel("Données cartographiques");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        
  
         

        int min = 0;
        int max = 100;
        int init = 100;
         
        
         
         
         final JSlider slider_4 = new JSlider(JSlider.HORIZONTAL,min, max, init);  
         slider_4.addChangeListener(new ChangeListener() {
        	 public void stateChanged(ChangeEvent event)
        	 	{
			 map().tr4 = (float)slider_4.getValue()/100;
             repaint();
       
        	 	} 
		
         });
         
         
         final JSlider slider_3 = new JSlider(JSlider.HORIZONTAL,min, max, init);  
         slider_3.addChangeListener(new ChangeListener() {
        	 public void stateChanged(ChangeEvent event)
        	 	{
			 map().tr3 = (float)slider_3.getValue()/100;
             repaint();

        	 	} 
		
         });
         
         
         
         final JSlider slider_2 = new JSlider(JSlider.HORIZONTAL,min, max, init);

         slider_2.addChangeListener(new ChangeListener() {
        	 public void stateChanged(ChangeEvent event)
        	 	{
			 map().tr2 = (float)slider_2.getValue()/100;
             repaint();
             SetDescription();

        	 	} 
		
         });
		


         final JSlider slider_1 = new JSlider(JSlider.HORIZONTAL,min, max, init);
         slider_1.addChangeListener(new ChangeListener() {
        	 public void stateChanged(ChangeEvent event)
        	 	{
			 map().tr1 = (float)slider_1.getValue()/100;
             repaint();

        	 	} 
		
         });
         
         final JSlider slider_grib = new JSlider(JSlider.HORIZONTAL,New_Test.t, (New_Test.recordCount/2)-1, New_Test.t);
         slider_grib.addChangeListener(new ChangeListener() {
        	 public void stateChanged(ChangeEvent event)
        	 	{
			  New_Test.t = slider_grib.getValue();
             map().removeAllMapMarkers();
             map().SetGrib();
             textMeteo.setText(New_Test.description);
             

        	 	} 
		
         });
         
         final JSlider slider_size = new JSlider(0, 16, (int)BoatMarker.D);
         slider_size.addChangeListener(new ChangeListener() {
        	 public void stateChanged(ChangeEvent event)
        	 	{
			  BoatMarker.D = slider_size.getValue();
			  BoatMarker.a = slider_size.getValue()/3;
             map().removeAllMapMarkers();
             map().SetGrib();
            
             

        	 	} 
        	 
         });
                        
         				JComboBox tileSourceSelector = new JComboBox(new TileSource[] { 
         						new OsmTileSource.EmptyMap(),
         		        		new OsmTileSource.Thun(),
         		                new OsmTileSource.Mapnik(),
         		                new OsmTileSource.SeaMap(), 
         		                new OsmTileSource.CycleMap(),
         		                new OsmTileSource.Topo(),
         		                new GeomodTileSources.GAidsToNavigation(), 
         		                new GeomodTileSources.GBase(), 
         		                new GeomodTileSources.GCoverage(),  
         		                new GeomodTileSources.GDangers(),
         		        		new GeomodTileSources.GHydro(),
         		        		new GeomodTileSources.GMetadata(),
         		        		new GeomodTileSources.GRestriction(),
         		        		new GeomodTileSources.GSoundings(),
         		        		new GeomodTileSources.GTopography(),
         		                new BingAerialTileSource(),
         		                new MapQuestOsmTileSource(), 
         		                new MapQuestOpenAerialTileSource() }
         						);
         				
                         tileSourceSelector.setSelectedIndex(1);
                         tileSourceSelector.addItemListener(new ItemListener() {
                             public void itemStateChanged(ItemEvent e) {
//                        map().tc1.setTileSource((TileSource) e.getItem());
//                        updateList(map().tc1);
                                 txtrTest.setText(listTileSourceNames); 
                             }
                         });
                         
                         final JCheckBox chckbxCheckBox1 = new JCheckBox("");
                         chckbxCheckBox1.setSelected(false);
                         chckbxCheckBox1.addActionListener(new ActionListener() {
                             public void actionPerformed(ActionEvent actionEvent) {         	
                             	map().DisplayTransparency1(chckbxCheckBox1.isSelected());
                             } });
                         
                         JComboBox tileSourceSelector2 = new JComboBox(new TileSource[] { 
                        			new OsmTileSource.EmptyMap(),
                            		new OsmTileSource.Thun(),
                                    new OsmTileSource.Mapnik(),
                                    new OsmTileSource.SeaMap(), 
                                    new OsmTileSource.CycleMap(), 
                                    new OsmTileSource.Topo(),
                                    new GeomodTileSources.GAidsToNavigation(), 
                                    new GeomodTileSources.GBase(), 
                                    new GeomodTileSources.GCoverage(),  
                                    new GeomodTileSources.GDangers(),
                            		new GeomodTileSources.GHydro(),
                            		new GeomodTileSources.GMetadata(),
                            		new GeomodTileSources.GRestriction(),
                            		new GeomodTileSources.GSoundings(),
                            		new GeomodTileSources.GTopography(),
                                    new BingAerialTileSource(),
                                    new MapQuestOsmTileSource(), 
                                    new MapQuestOpenAerialTileSource() }
                        		 );
                         tileSourceSelector2.setSelectedIndex(0);
                         
                         final JCheckBox chckbxCheckBox2 = new JCheckBox("");
                         chckbxCheckBox2.setSelected(false);
                         chckbxCheckBox2.addActionListener(new ActionListener() {
                             public void actionPerformed(ActionEvent actionEvent) {         	
                             	map().DisplayTransparency2(chckbxCheckBox2.isSelected());
                             } });
                                                
                         JComboBox tileSourceSelector3 = new JComboBox(new TileSource[] { 
                        			new OsmTileSource.EmptyMap(),
                            		new OsmTileSource.Thun(),
                                    new OsmTileSource.Mapnik(),
                                    new OsmTileSource.SeaMap(), 
                                    new OsmTileSource.CycleMap(), 
                                    new OsmTileSource.Topo(),
                                    new GeomodTileSources.GAidsToNavigation(), 
                                    new GeomodTileSources.GBase(), 
                                    new GeomodTileSources.GCoverage(),  
                                    new GeomodTileSources.GDangers(),
                            		new GeomodTileSources.GHydro(),
                            		new GeomodTileSources.GMetadata(),
                            		new GeomodTileSources.GRestriction(),
                            		new GeomodTileSources.GSoundings(),
                            		new GeomodTileSources.GTopography(),
                                    new BingAerialTileSource(),
                                    new MapQuestOsmTileSource(), 
                                    new MapQuestOpenAerialTileSource() }
                        		 );
                         tileSourceSelector3.setSelectedIndex(0);
                         
                         final JCheckBox chckbxCheckBox3 = new JCheckBox("");
                         chckbxCheckBox3.setSelected(false);
                         chckbxCheckBox3.addActionListener(new ActionListener() {
                             public void actionPerformed(ActionEvent actionEvent) {         	
                             	map().DisplayTransparency3(chckbxCheckBox3.isSelected());
                             } });
                         
                         JComboBox tileSourceSelector4 = new JComboBox(new TileSource[] { 
                         		new OsmTileSource.EmptyMap(),
                         		new OsmTileSource.Thun(),
                                 new OsmTileSource.Mapnik(),
                                 new OsmTileSource.SeaMap(), 
                                 new OsmTileSource.CycleMap(), 
                                 new OsmTileSource.Topo(),
                                 new GeomodTileSources.GAidsToNavigation(), 
                                 new GeomodTileSources.GBase(), 
                                 new GeomodTileSources.GCoverage(),  
                                 new GeomodTileSources.GDangers(),
                         		new GeomodTileSources.GHydro(),
                         		new GeomodTileSources.GMetadata(),
                         		new GeomodTileSources.GRestriction(),
                         		new GeomodTileSources.GSoundings(),
                         		new GeomodTileSources.GTopography(),
                                 new BingAerialTileSource(),
                                 new MapQuestOsmTileSource(), 
                                 new MapQuestOpenAerialTileSource() }
                         		);
                         tileSourceSelector4.setSelectedIndex(0);
                         
                          tileSourceSelector4.addItemListener(new ItemListener() {
                              public void itemStateChanged(ItemEvent e) {            	
                                  map().tc4.setTileSource((TileSource) e.getItem()); 
                                  updateList(map().tc4);
                                  txtrTest.setText(listTileSourceNames);     
                              }
                          });
                          
                          final JCheckBox chckbxCheckBox4 = new JCheckBox("");
                          chckbxCheckBox4.setSelected(false);
                          chckbxCheckBox4.addActionListener(new ActionListener() {
                              public void actionPerformed(ActionEvent actionEvent) {         	
                              	map().DisplayTransparency4(chckbxCheckBox4.isSelected());
                              } });
                         
                         JLabel label = new JLabel("Données Météorologiques :");
                         
                         textMeteo = new JTextArea();
                        
                         textMeteo.setText(New_Test.description);
                         textMeteo.setRows(10);
                         textMeteo.setEditable(false);
                         textMeteo.setBackground(new Color(169, 169, 169));
                         
                        
                         
                        
                         
                         
                         GroupLayout gl_panel_1 = new GroupLayout(panel_1);
                         gl_panel_1.setHorizontalGroup(
                         	gl_panel_1.createParallelGroup(Alignment.LEADING)
                         		.addGroup(gl_panel_1.createSequentialGroup()
                         			.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                         				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
                         				.addComponent(txtrTest, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
                         				.addGroup(gl_panel_1.createSequentialGroup()
                         					.addGap(4)
                         					.addComponent(chckbxCheckBox4)
                         					.addGap(8)
                         					.addComponent(tileSourceSelector4, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
                         				.addGroup(gl_panel_1.createSequentialGroup()
                         					.addGap(14)
                         					.addComponent(slider_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                         				.addGroup(gl_panel_1.createSequentialGroup()
                         					.addContainerGap()
                         					.addComponent(chckbxCheckBox3)
                         					.addGap(6)
                         					.addComponent(tileSourceSelector3, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
                         				.addGroup(gl_panel_1.createSequentialGroup()
                         					.addGap(14)
                         					.addComponent(slider_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                         				.addGroup(gl_panel_1.createSequentialGroup()
                         					.addContainerGap()
                         					.addComponent(chckbxCheckBox2)
                         					.addGap(6)
                         					.addComponent(tileSourceSelector2, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
                         				.addGroup(gl_panel_1.createSequentialGroup()
                         					.addGap(14)
                         					.addComponent(slider_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                         				.addGroup(gl_panel_1.createSequentialGroup()
                         					.addGap(14)
                         					.addComponent(slider_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                         				.addGroup(gl_panel_1.createSequentialGroup()
                         					.addGap(4)
                         					.addComponent(chckbxCheckBox1)
                         					.addGap(8)
                         					.addComponent(tileSourceSelector, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
                         				.addComponent(label, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
                         				.addGroup(gl_panel_1.createSequentialGroup()
                         					.addContainerGap()
                         					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                         						.addGroup(gl_panel_1.createSequentialGroup()
                         							.addComponent(slider_grib, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         							.addGap(29))
                         						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
                         							.addComponent(slider_size, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                         							.addComponent(textMeteo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                         			.addGap(20))
                         );
                         gl_panel_1.setVerticalGroup(
                         	gl_panel_1.createParallelGroup(Alignment.LEADING)
                         		.addGroup(gl_panel_1.createSequentialGroup()
                         			.addComponent(lblNewLabel)
                         			.addComponent(txtrTest, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                         			.addGap(18)
                         			.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                         				.addComponent(chckbxCheckBox4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                         				.addGroup(gl_panel_1.createSequentialGroup()
                         					.addGap(3)
                         					.addComponent(tileSourceSelector4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                         			.addGap(6)
                         			.addComponent(slider_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                         			.addGap(7)
                         			.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                         				.addComponent(tileSourceSelector3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                         				.addComponent(chckbxCheckBox3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                         			.addGap(7)
                         			.addComponent(slider_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                         			.addGap(10)
                         			.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                         				.addComponent(tileSourceSelector2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                         				.addComponent(chckbxCheckBox2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                         			.addPreferredGap(ComponentPlacement.RELATED)
                         			.addComponent(slider_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                         			.addGap(7)
                         			.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                         				.addComponent(chckbxCheckBox1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                         				.addComponent(tileSourceSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                         			.addGap(7)
                         			.addComponent(slider_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                         			.addGap(18)
                         			.addComponent(label)
                         			.addGap(18)
                         			.addComponent(slider_grib, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                         			.addPreferredGap(ComponentPlacement.UNRELATED)
                         			.addComponent(textMeteo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                         			.addGap(18)
                         			.addComponent(slider_size, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                         			.addContainerGap(567, Short.MAX_VALUE))
                         );
                         panel_1.setLayout(gl_panel_1);
                         tileSourceSelector3.addItemListener(new ItemListener() {
                         	
                         	
                             public void itemStateChanged(ItemEvent e) {            	
                                 map().tc3.setTileSource((TileSource) e.getItem()); 
                                 updateList(map().tc3);
                                 txtrTest.setText(listTileSourceNames);
                             }
                             
                         });
                         tileSourceSelector2.addItemListener(new ItemListener() {
                             public void itemStateChanged(ItemEvent e) {            	
                                 map().tc2.setTileSource((TileSource) e.getItem()); 
                                 updateList(map().tc2);
                                 txtrTest.setText(listTileSourceNames);
                                
                             }
                             
                         });
                         tileSourceSelector.addItemListener(new ItemListener() {
                         public void itemStateChanged(ItemEvent e) {            	
                             map().tc1.setTileSource((TileSource) e.getItem()); 
                             updateList(map().tc1);
                             txtrTest.setText(listTileSourceNames);       
                         }
                         
                         
                         
                         
                         
                         
            });

        getContentPane().add(treeMap, BorderLayout.CENTER);

        //
        

        
        
        
//        LayerGroup germanyGroup = new LayerGroup("Germany");
//        Layer germanyWestLayer = germanyGroup.addLayer("Germany West");
//        Layer germanyEastLayer = germanyGroup.addLayer("Germany East");
//        MapMarkerDot eberstadt = new MapMarkerDot(germanyEastLayer, "Eberstadt", 49.814284999, 8.642065999);
//        MapMarkerDot ebersheim = new MapMarkerDot(germanyWestLayer, "Ebersheim", 49.91, 8.24);
//        MapMarkerDot empty = new MapMarkerDot(germanyEastLayer, 49.71, 8.64);
//        MapMarkerDot darmstadt = new MapMarkerDot(germanyEastLayer, "Darmstadt", 49.8588, 8.643);
//        map().addMapMarker(eberstadt);
//        map().addMapMarker(ebersheim);
//        map().addMapMarker(empty);
//        Layer franceLayer = treeMap.addLayer("France");
//        map().addMapMarker(new MapMarkerDot(franceLayer, "La Gallerie", 48.71, -1));
//        map().addMapMarker(new MapMarkerDot(43.604, 1.444));
//        map().addMapMarker(new MapMarkerCircle(53.343, -6.267, 0.666));
//        map().addMapRectangle(new MapRectangleImpl(new Coordinate(53.343, -6.267), new Coordinate(43.604, 1.444)));
//        map().addMapMarker(darmstadt);
//        treeMap.addLayer(germanyWestLayer);
//        treeMap.addLayer(germanyEastLayer);

//        MapPolygon bermudas = new MapPolygonImpl(c(49,1), c(45,10), c(40,5));
//        map().addMapPolygon( bermudas );
//        map().addMapPolygon( new MapPolygonImpl(germanyEastLayer, "Riedstadt", ebersheim, darmstadt, eberstadt, empty));
//
//        map().addMapMarker(new MapMarkerCircle(germanyWestLayer, "North of Suisse", new Coordinate(48, 7), .5));
//        Layer spain = treeMap.addLayer("Spain");
//        map().addMapMarker(new MapMarkerCircle(spain, "La Garena", new Coordinate(40.4838, -3.39), .002));
//        spain.setVisible(false);

//        Layer wales = treeMap.addLayer("UK");
//        map().addMapRectangle(new MapRectangleImpl(wales, "Wales", c(53.35,-4.57), c(51.64,-2.63)));


        // map.setDisplayPosition(new Coordinate(49.807, 8.6), 11);
        // map.setTileGridVisible(true);

        map().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                	
                	map().globalSet();
                    map().getAttribution().handleAttribution(e.getPoint(), true);
                }
            }
        });

        map().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
            	
                Point p = e.getPoint();
               
            	
                boolean cursorHand = map().getAttribution().handleAttributionCursor(p);
                if (cursorHand) {
                    map().setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    map().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                if(showToolTip.isSelected()) map().setToolTipText(map().getPosition(p).toString());
            }
        });
    }
    
  
	private JMapViewer map(){
        return treeMap.getViewer();
    }
    
    private static Coordinate c(double lat, double lon){
        return new Coordinate(lat, lon);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // java.util.Properties systemProperties = System.getProperties();
        // systemProperties.setProperty("http.proxyHost", "localhost");
        // systemProperties.setProperty("http.proxyPort", "8008");
        new Demo().setVisible(true);
        
    }

    private void updateZoomParameters() {
        if (mperpLabelValue!=null)
            mperpLabelValue.setText(String.format("%s",map().getMeterPerPixel()));
        if (zoomValue!=null)
            zoomValue.setText(String.format("%s", map().getZoom()));
    }

    @Override
    public void processCommand(JMVCommandEvent command) {
        if (command.getCommand().equals(JMVCommandEvent.COMMAND.ZOOM) ||
                command.getCommand().equals(JMVCommandEvent.COMMAND.MOVE)) {
            updateZoomParameters();
        }
        
        
    }
    
    public void addList() {	
    	listTileSourceNames = "";
    	Collections.reverse(map().tileControllers);
      for(TileController tileController : treeMap.getViewer().getTileControllers())
      {	  
		listTileSourceNames += "  • " + tileController.getTileSource().getName() + "\n";
      }
    }
    
    
    
    public void updateList(TileController tileController) {     	
    	listTileSourceNames = "";
      for(TileController tileController1 : treeMap.getViewer().getTileControllers())
      {  	  	
			listTileSourceNames += "  • " + tileController1.getTileSource().getName() + "\n";
      }
    }
    
    public void SetDescription() { 
        
    }
}
