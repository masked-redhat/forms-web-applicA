package me.skhull.formswebapplication.database;

import me.skhull.formswebapplication.Utilities.Utilities;
import me.skhull.formswebapplication.models.LANPortRequest;
import me.skhull.formswebapplication.models.ProjectEmployeesAndOthersRequest;
import me.skhull.formswebapplication.models.VMReallocationRequest;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class DatabaseOps {
    protected static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException
    {
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/";

        // Database name to access
        String dbName = "testdb";
        String dbUsername = "root";
        String dbPassword = "iamthebest";

        Connection con = null;

        try{
            Class.forName(dbDriver);
            con = DriverManager.getConnection(dbURL + dbName,
                    dbUsername,
                    dbPassword);
            System.out.println("Connected to database");}
        catch (SQLException e){
            return con;
        }

        return con;
    }

    public static boolean insertData(LANPortRequest request, Date date, String ref) throws SQLException {
        Connection con = null;
        try {
            // Initialize the database
            con = initializeDatabase();

            // Create Prepared Statement
            PreparedStatement st = prepareSqlStatement(con, request, Utilities.getCurrentSqlDate(date), ref);

            // Execute the insert Command
            st.executeUpdate();

            st.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            con.close();
        }
    }

    public static boolean insertData(ProjectEmployeesAndOthersRequest request, Date date, String ref) throws SQLException {
        Connection con = null;
        try {
            // Initialize the database
            con = initializeDatabase();

            // Create Prepared Statement
            PreparedStatement st = prepareSqlStatement(con, request, Utilities.getCurrentSqlDate(date), ref);

            // Execute the insert Command
            st.executeUpdate();

            st.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            con.close();
        }
    }

    public static boolean insertData(VMReallocationRequest request, Date date, String ref) throws SQLException {
        Connection con = null;
        try {
            // Initialize the database
            con = initializeDatabase();

            // Create Prepared Statement
            PreparedStatement st = prepareSqlStatement(con, request, Utilities.getCurrentSqlDate(date), ref);

            // Execute the insert Command
            st.executeUpdate();

            st.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            con.close();
        }
    }

    public static PreparedStatement prepareSqlStatement(Connection con, LANPortRequest request, java.sql.Date formFilledDate, String ref) throws SQLException {
        PreparedStatement st = con.prepareStatement("insert into lanportrequests(name, unit, email, phone, pf_number, description, form_filled_date, ref) values(?,?,?,?,?,?,?,?)");

        st.setString(1, request.getName());
        st.setString(2, request.getUnit());
        st.setString(3, request.getEmail());
        st.setString(4, request.getPhone());
        st.setString(5, request.getPfNumber());
        st.setString(6, request.getDescription());
        st.setDate(7, formFilledDate);
        st.setString(8, ref);

        return st;
    }

    public static PreparedStatement prepareSqlStatement(Connection con, ProjectEmployeesAndOthersRequest request, java.sql.Date formFilledDate, String ref) throws SQLException {
        PreparedStatement st = con.prepareStatement("insert into projectemployeerequests(first_name, middle_name, last_name, pf_number, project_number, project_investigator_full_name, project_investigator_address, account_type, applying_for, old_login_id, old_login_pf_number, contact_personal, contact_office, amount, date_in_form, form_filled_date, ref) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        String applyingFor = "New Account";
        if (Objects.equals(request.isFreshAccount(), "New")) {applyingFor = "New account";}
        else if (Objects.equals(request.isFreshAccount(), "Renew")){applyingFor = "Renewal of old account";}

        st.setString(1, request.getFirstName());
        st.setString(2, request.getMiddleName());
        st.setString(3, request.getSurname());
        st.setString(4, request.getPfNumber());
        st.setString(5, request.getProjectNumber());
        st.setString(6, request.getProjectInvestigatorFullName());
        st.setString(7, request.getProjectInvestigatorAddress());
        st.setString(8, request.getTypeAccount());
        st.setString(9, applyingFor);
        st.setString(10, request.getOldLoginID());
        st.setString(11, request.getOldPfNumber());
        st.setString(12, request.getContactPersonal());
        st.setString(13, request.getContactOffice());
        st.setInt(14, request.getAmt());
        st.setString(15, request.getDate());
        st.setDate(16, formFilledDate);
        st.setString(17, ref);

        return st;
    }

    public static PreparedStatement prepareSqlStatement(Connection con, VMReallocationRequest request, java.sql.Date formFilledDate, String ref) throws SQLException {
        PreparedStatement st = con.prepareStatement("insert into vmreallocationrequests(name, pf_number, department, email, phone, ip_address, full_domain_name, services, purpose_vm, form_filled_date, ref) values(?,?,?,?,?,?,?,?,?,?,?)");

        st.setString(1, request.getUserName());
        st.setString(2, request.getPfNumber());
        st.setString(3, request.getDepartment());
        st.setString(4, request.getEmail());
        st.setString(5, request.getMobileNumber());
        st.setString(6, request.getVmIp());
        st.setString(7, request.getVmFullDomainName());
        st.setString(8, Arrays.toString(request.getServices()));
        st.setString(9, request.getVmPurpose());
        st.setDate(10, formFilledDate);
        st.setString(11, ref);

        return st;
    }
}
