package com.example.ferooz.horticulture.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final String TAG = "GescomDBHelper";
	private static DBHelper mInstance;
	private static Context context;

	public static DBHelper getInstance(Context context) {
		DBHelper.context = context;
		if (mInstance == null) {
			mInstance = new DBHelper(context);
		}
		return mInstance;
	}

	private DBHelper(Context context) {
		super(context, DBConstants.DATABASE_NAME, null,
				DBConstants.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG,"Inside DataBase Creator") ;
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			//Document doc = docBuilder.parse(context.getAssets().open("GescomDatBaseFile.xml"));//Old base rates. 
			Document doc = docBuilder.parse(context.getAssets().open("cams.xml"));//latest base rates.
			NodeList items = doc.getElementsByTagName("schema");
			for (int i = 0; i < items.getLength(); ++i) {
				Element node = (Element) items.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String Schema = node.getTextContent().trim();
					Log.d(TAG,Schema) ;
					db.execSQL(Schema);
				}
			}

			NodeList inserts = doc.getElementsByTagName("insert");
			for (int i = 0; i < inserts.getLength(); ++i) {
				Element node = (Element) inserts.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String insert = node.getTextContent().trim();
					Log.d(TAG,insert) ;
					db.execSQL(insert);
				}
			}
			updateDatabase(db);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		
		updateDatabase(db,oldVersion,newVersion);
		
		switch (newVersion) {

		case 8:
		
			break;
		default:
			break;
		}
	}
	
	private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion){

		try {
			
			//db.execSQL("Create table ScriptStatus (VersionNumber text, ScriptId int, Status text, ExecutedDateTime text, Message text)");
			
			//db.execSQL("ALTER TABLE Form_Two_Downloaded_Experiment_Details ADD COLUMN ShowNo Text"); Form_One_Downloaded_Experiment_Details
			  
			db.execSQL("ALTER TABLE Village_Change_Details ADD COLUMN IsUploaded text");
			 
			db.execSQL("ALTER TABLE Form_One_Downloaded_Experiment_Details ADD COLUMN IsExpIdSent text");
			
			db.execSQL("ALTER TABLE Master_Village ADD COLUMN Year_Code int");
			db.execSQL("ALTER TABLE Master_Village ADD COLUMN Season_Code int");
			
			
			 
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			Document doc = docBuilder.parse(context.getAssets().open("ccelstdatabaseUpdateQueryFile.xml"));//latest base rates.

			NodeList inserts = doc.getElementsByTagName("schema");
			for (int i = 0; i < inserts.getLength(); ++i) {
				Element node = (Element) inserts.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String insert = node.getTextContent().trim();
					Log.d(TAG,insert) ;
					db.execSQL(insert);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void updateDatabase(SQLiteDatabase db){

		try {
			
			//db.execSQL("Create table ScriptStatus (VersionNumber text, ScriptId int, Status text, ExecutedDateTime text, Message text)");
			
			//  db.execSQL("ALTER TABLE Form_Two_Downloaded_Experiment_Details ADD COLUMN ShowNo Text");
			  
			  db.execSQL("ALTER TABLE Village_Change_Details ADD COLUMN IsUploaded text");
			  
			  db.execSQL("ALTER TABLE Form_One_Downloaded_Experiment_Details ADD COLUMN IsExpIdSent text");
			  
			  db.execSQL("ALTER TABLE Master_Village ADD COLUMN Year_Code int");
			  db.execSQL("ALTER TABLE Master_Village ADD COLUMN Season_Code int");
				
			  
				 
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			Document doc = docBuilder.parse(context.getAssets().open("ccelstdatabaseUpdateQueryFile.xml"));//latest base rates.

			NodeList inserts = doc.getElementsByTagName("schema");
			for (int i = 0; i < inserts.getLength(); ++i) {
				Element node = (Element) inserts.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String insert = node.getTextContent().trim();
					Log.d(TAG,insert) ;
					db.execSQL(insert);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public static DBHelper getInstance(SoapProxy context2) {
		// TODO Auto-generated method stub
		return null;
	}*/
}
