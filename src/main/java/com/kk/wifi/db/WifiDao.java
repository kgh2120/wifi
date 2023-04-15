package com.kk.wifi.db;

import com.kk.wifi.vo.WifiVO;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class WifiDao {

    private Connection conn;

    private void init() {
        conn = DBConnection.getConnection();
    }

    private void end() {
        DBConnection.closeConnection();
    }


    public void insertWifis(List<WifiVO> vo) {
        init();
        int count = 0;
        int batchLimit = 100;
        try {
            String insertSql = "insert into wifi (" + getWifiFieldsName() + ") "
                    + "values " + getInsertFieldQuestionMark(16);
            System.out.println(insertSql);
            PreparedStatement st = null;
            st = conn.prepareStatement(insertSql);
            for (WifiVO wifiVO : vo) {
                System.out.println(count);
                insertFieldDataIntoPrepareStatement(st, wifiVO);
                st.addBatch();
                count++;
                if(count % 100 == 0)
                    st.executeBatch();
            }

            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("----Complete!!-----");
        end();
    }

    private void insertFieldDataIntoPrepareStatement(PreparedStatement st, WifiVO vo)
            throws IllegalAccessException, SQLException {
        Field[] fields = vo.getClass().getDeclaredFields();
        int idx = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            st.setString(idx++, (String) field.get(vo));
        }
    }

    private String getWifiFieldsName() {
        Class<WifiVO> wifiVOClass = WifiVO.class;
        Field[] fields = wifiVOClass.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {

            sb.append(field.getName()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String getInsertFieldQuestionMark(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < n - 1; i++) {
            sb.append("?, ");
        }
        sb.append("? )");
        return sb.toString();
    }

}
