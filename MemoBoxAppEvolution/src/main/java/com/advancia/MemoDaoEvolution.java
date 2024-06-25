package com.advancia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.advancia.models.Memo;
import com.advancia.models.SlotNumber;
import com.advancia.models.SlottedMemo;

public class MemoDaoEvolution {
	JdbcConnector dao = new JdbcConnector();

	public void sendMemosToDataBase(SlotNumber slotNumber, Memo memoContent) {
		Connection connection = null;
		try {
			connection = dao.getConnection();
			Statement st = connection.createStatement();
			st.executeQuery("INSERT INTO MEMOS VALUES(" + slotNumber + ",'" + memoContent + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("added to DataBase");
	}

	public List<SlottedMemo> getMemoFromDb(){
		List<SlottedMemo> memoList = new ArrayList<>();
		Connection connection = null;
		try { 
			connection = dao.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM MEMOS");
			//System.out.println("resultsetprint" + resultSet.isClosed());
			while (resultSet.next()) {
				SlotNumber memoId = new SlotNumber(resultSet.getInt("MEMO_ID"));
				Memo memoContent = new Memo(resultSet.getString("MEMO_CONTENT"));
				SlottedMemo fetchedData = new SlottedMemo(memoId, memoContent);
				memoList.add(fetchedData);
				//System.out.println(resultSet.getInt("MEMO_ID") + " value: " + resultSet.getString("MEMO_CONTENT"));
			}
			System.out.println("Memo list size in getDatafromDb: " + memoList.size());
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return memoList;
	}

	public void removeMemoFromDataBase(SlotNumber slotNumber) {
		Connection connection = null;
		try {
			connection = dao.getConnection();
			Statement statement = connection.createStatement();
			statement.executeQuery("DELETE FROM MEMOS WHERE MEMO_ID = " + slotNumber);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}
}
