package sample;

import XML.TDA.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by usuario on 28/05/17.
 */
public class MySQL {
    private Connection Conexion;
    private String dbuser = "admin";
    private String dbpass = "anita05";
    private String dbname = "chinook";

    public void Conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname, dbuser, dbpass);
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<PlayList> getPlayLists(){
        ObservableList<PlayList> list= FXCollections.observableArrayList();

        try{
            String query = "SELECT * from Playlist;";

            Statement st = Conexion.createStatement();
            ResultSet rs= st.executeQuery(query);
            while(rs.next())
                list.add(new PlayList(rs.getInt("PlaylistId"), rs.getString("Name")));
        }
        catch(SQLException ex){

        }
        return list;
    }
    public ObservableList<Empleado> getVendedores(){
        ObservableList<Empleado> list=FXCollections.observableArrayList();

        try {
            String query = "SELECT e.EmployeeId, e.LastName, e.FirstName, e.Title, e.ReportsTo, e.BirthDate, e.HireDate,"
                    + " e.Address, e.City, e.State, e.Country, e.PostalCode, e.Phone, e.Fax, e.Email, j.FirstName as NombreBoss, j.LastName as ApellidoBoss"
                    + " FROM Employee e join Employee j on e.Reportsto=j.EmployeeId where e.Title='Sales Support Agent';";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                list.add(new Empleado(
                        rs.getInt("EmployeeId"),
                        rs.getString("LastName"),
                        rs.getString("FirstName"),
                        rs.getString("Title"),
                        rs.getInt("Reportsto"),
                        rs.getDate("BirthDate"),
                        rs.getDate("HireDate"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getString("State"),
                        rs.getString("Country"),
                        rs.getString("PostalCode"),
                        rs.getString("Phone"),
                        rs.getString("Fax"),
                        rs.getString("Email"),
                        rs.getString("NombreBoss")+ " "+rs.getString("ApellidoBoss")

                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex.getLocalizedMessage());
        }

        return list;
    }
    public ObservableList<Cliente> getCompradores(){
        ObservableList<Cliente> list=FXCollections.observableArrayList();

        try {
            String query = "SELECT c.CustomerId, c.FirstName, c.LastName, c.Company, c.Address, c.City, c.State, c.Country, c.PostalCode, c.Phone, c.Fax, c.Email, c.SupportRepId, e.FirstName as NombreVendedor, e.LastName as ApellidoVendedor from Customer c join Employee e on c.SupportRepId=e.EmployeeId;";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                list.add(new Cliente(
                        rs.getInt("CustomerId"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Company"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getString("State"),
                        rs.getString("Country"),
                        rs.getString("PostalCode"),
                        rs.getString("Phone"),
                        rs.getString("Fax"),
                        rs.getString("Email"),
                        rs.getInt("SupportRepId"),
                        rs.getString("NombreVendedor")+ " "+rs.getString("ApellidoVendedor")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex.getLocalizedMessage());
        }

        return list;
    }


    public ObservableList<Track> getSongs() {

        ObservableList<Track> list=FXCollections.observableArrayList();

        try {
            String query = "select TrackId, t.Name as name, a.Title as album, ar.Name as artist, m.Name as medio, g.Name as genre, Composer, Milliseconds, Bytes, UnitPrice "
                    + "from Track t join Album a on t.AlbumId=a.AlbumId join MediaType m on t.MediaTypeId=m.MediaTypeId "
                    + "join Genre g on t.GenreId=g.GenreId join Artist ar on a.ArtistId=ar.ArtistId order by ar.Name, a.Title, t.Name";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                list.add(new Track(
                        rs.getInt("TrackId"),
                        rs.getString("name"),
                        rs.getString("album"),
                        rs.getString("Composer"),
                        rs.getString("medio"),
                        rs.getString("genre"),
                        rs.getString("artist"),
                        rs.getInt("Milliseconds"),
                        rs.getInt("Bytes"),
                        rs.getDouble("UnitPrice")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex.getLocalizedMessage());
        }
        return list;
    }
    public ObservableList<Artista> getArtists(){
        ObservableList<Artista> list=FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM Artist order by name";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                list.add(new Artista(
                        rs.getInt("ArtistId"),
                        rs.getString("Name")

                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex.getLocalizedMessage());
        }
        return list;
    }
    public ObservableList<Genero>getGenres(){
        ObservableList<Genero> list=FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM Genre";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                list.add(new Genero(
                        rs.getInt("GenreId"),
                        rs.getString("Name")

                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex.getLocalizedMessage());
        }
        return list;
    }
    public ObservableList<Track> getPlaylistSongs(PlayList pl){
        ObservableList<Track> list=FXCollections.observableArrayList();

        try {
            String query = "SELECT t.Name, t.AlbumId, t.MediaTypeId, t.GenreId, t.Composer, t.Milliseconds, t.Bytes, t.UnitPrice from Playlist pl join PlaylistTrack plt on pl.PlaylistId=plt.PlaylistId join Track t on plt.TrackId=t.TrackId where pl.PlaylistId = ? ; ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, pl.getPlaylist_id());
            ResultSet rs;
            rs = st.executeQuery();
            while(rs.next()){
                list.add(new Track(
                        rs.getString("Name"),
                        rs.getInt("AlbumId"),
                        rs.getInt("MediaTypeId"),
                        rs.getInt("GenreId"),
                        rs.getString("Composer"),
                        rs.getInt("Milliseconds"),
                        rs.getInt("Bytes"),
                        rs.getDouble("UnitPrice")

                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex.getLocalizedMessage());
        }
        return list;
    }
    public ObservableList<MediaType> getMedias(){
        ObservableList<MediaType> list=FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM MediaType";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                list.add(new MediaType(
                        rs.getInt("MediaTypeId"),
                        rs.getString("Name")

                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex.getLocalizedMessage());
        }
        return list;
    }
    public ObservableList<Album> getAlbums(){
        ObservableList<Album> list=FXCollections.observableArrayList();

        try {
            String query = "SELECT al.AlbumId, al.Title, al.ArtistId, ar.Name as Artist"
                    + " from Album al join Artist ar on al.ArtistId=ar.ArtistId";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                list.add(new Album(
                        rs.getInt("AlbumId"),
                        rs.getString("Title"),
                        rs.getInt("ArtistId"),
                        rs.getString("Artist")


                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex.getLocalizedMessage());
        }
        return list;
    }
    public ObservableList<Empleado> getEmployees() {


        ObservableList<Empleado> list=FXCollections.observableArrayList();

        try {
            String query = "SELECT e.EmployeeId, e.LastName, e.FirstName, e.Title, e.ReportsTo, e.BirthDate, e.HireDate,"
                    + " e.Address, e.City, e.State, e.Country, e.PostalCode, e.Phone, e.Fax, e.Email, j.FirstName as NombreBoss, j.LastName as ApellidoBoss"
                    + " FROM Employee e join Employee j on e.Reportsto=j.EmployeeId;";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                list.add(new Empleado(
                        rs.getInt("EmployeeId"),
                        rs.getString("LastName"),
                        rs.getString("FirstName"),
                        rs.getString("Title"),
                        rs.getInt("Reportsto"),
                        rs.getDate("BirthDate"),
                        rs.getDate("HireDate"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getString("State"),
                        rs.getString("Country"),
                        rs.getString("PostalCode"),
                        rs.getString("Phone"),
                        rs.getString("Fax"),
                        rs.getString("Email"),
                        rs.getString("NombreBoss")+ " "+rs.getString("ApellidoBoss")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex.getLocalizedMessage());
        }
        return list;
    }
    public boolean addArtist(String name){
        try{
            String query = "INSERT INTO Artist (Name) values (?) ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, name);
            st.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al ingresar información..."+ex.getLocalizedMessage());
        }

        return false;
    }
    public boolean addAlbum(Album nuevoAlbum){
        try{
            String query = "INSERT INTO Album (Title, ArtistId) values (?,?) ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, nuevoAlbum.getNombre());
            st.setInt(2, nuevoAlbum.getArtista_id());
            st.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al ingresar información..."+ex.getLocalizedMessage());
        }

        return false;
    }
    public boolean addGenre(String name){
        try{
            String query = "INSERT INTO Genre (Name) values (?) ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, name);
            st.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al ingresar información..."+ex.getLocalizedMessage());
        }

        return false;
    }
    public boolean addItemToPlayList(Track t, PlayList p){
        try{
            String query = "INSERT INTO PlaylistTrack (PlaylistId, TrackId) values (?,?) ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, p.getPlaylist_id());
            st.setInt(2, t.getTrackId());
            st.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al ingresar información..."+ex.getLocalizedMessage());
        }

        return false;
    }
    public boolean addPlayList(PlayList p){
        try{
            String query = "INSERT INTO Playlist (Name) values (?) ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, p.getName());
            st.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al ingresar información..."+ex.getLocalizedMessage());
        }

        return false;
    }
    public boolean addMedio(String name){
        try{
            String query = "INSERT INTO MediaType (Name) values (?) ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, name);
            st.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al ingresar información..."+ex.getLocalizedMessage());
        }

        return false;
    }
    public boolean addInvoice(Invoice i){
        try{
            String query = "INSERT INTO Invoice (CustomerId, InvoiceDate, BillingAddress, BillingCity, BillingState, BillingCountry, BillingPostalCode, Total) values (?,?,?,?,?,?,?,?) ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, i.getCustomer_id());
            st.setDate(2, i.getInvoice_date());
            st.setString(3, i.getBilling_address());
            st.setString(4, i.getBilling_city());
            st.setString(5, i.getBilling_state());
            st.setString(6, i.getBilling_country());
            st.setString(7, i.getBilling_postal_code());
            st.setDouble(8, i.getTotal());
            st.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al ingresar información..."+ex.getLocalizedMessage());
        }

        return false;
    }
    public boolean addInvoiceLine(InvoiceLine i){
        try{
            String query = "INSERT INTO InvoiceLine (InvoiceId, TrackId, UnitPrice, Quantity) values (?,?,?,?) ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, i.getInvoice_id());
            st.setInt(2, i.getTrack_id());
            st.setDouble(3, i.getUnit_price());
            st.setInt(4, i.getQuantity());
            st.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al ingresar información..."+ex.getLocalizedMessage());
        }

        return false;
    }
    public Invoice searchInvoice(Invoice i){
        try{
            String query ="select * from Invoice where CustomerId=? and InvoiceDate=? and BillingAddress=? and BillingCity=? and BillingState=? and BillingCountry=? and BillingPostalCode=? and Total=? ; ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, i.getCustomer_id());
            st.setDate(2, i.getInvoice_date());
            st.setString(3, i.getBilling_address());
            st.setString(4, i.getBilling_city());
            st.setString(5, i.getBilling_state());
            st.setString(6, i.getBilling_country());
            st.setString(7, i.getBilling_postal_code());
            st.setDouble(8, i.getTotal());
            ResultSet rs= st.executeQuery();
            if (rs.next()){
                i.setInvoice_id(rs.getInt("InvoiceId"));
                return i;
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar la factura.."+ex.getLocalizedMessage());
        }

        return null;
    }
    public boolean addCustomer(Cliente nuevoComprador){
        try {
            String query = "insert into Customer (FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId)  values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = Conexion.prepareStatement(query);

            st.setString(1, nuevoComprador.getFirst_name());
            st.setString(2, nuevoComprador.getLast_name());
            st.setString(3, nuevoComprador.getCompany());
            st.setString(4, nuevoComprador.getAddress());
            st.setString(5, nuevoComprador.getCity());
            st.setString(6, nuevoComprador.getState());
            st.setString(7, nuevoComprador.getCountry());
            st.setString(8, nuevoComprador.getPostal_code());
            st.setString(9, nuevoComprador.getPhone());
            st.setString(10, nuevoComprador.getFax());
            st.setString(11, nuevoComprador.getEmail());
            st.setInt(12, nuevoComprador.getSupport_rep_id());


            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;

    }
    public boolean modifyCustomer(Cliente old, Cliente nuevo){
        try{
            String query = "update Customer set FirstName=?,LastName=?, Company=?, Address=?, City=?, State=?, Country=?, PostalCode=?, Phone=?, Fax=?, Email=?, SupportRepId=? where CustomerId=?";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, nuevo.getFirst_name());
            st.setString(2, nuevo.getLast_name());
            st.setString(3, nuevo.getCompany());
            st.setString(4, nuevo.getAddress());
            st.setString(5, nuevo.getCity());
            st.setString(6, nuevo.getState());
            st.setString(7, nuevo.getCountry());
            st.setString(8, nuevo.getPostal_code());
            st.setString(9, nuevo.getPhone());
            st.setString(10, nuevo.getFax());
            st.setString(11, nuevo.getEmail());
            st.setInt(12, nuevo.getSupport_rep_id());
            st.setInt(13, old.getId());
            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }

        return false;
    }
    public boolean modifyArtist(Artista old, Artista nuevo){
        try{
            String query = "update Artist set Name=? where ArtistID=?";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, nuevo.getNombre());
            st.setInt(2, old.getId());
            st.execute();
            return true;
        }
        catch(SQLException ex){
        }

        return false;
    }
    public boolean modifyAlbum(Album old, Album nuevo){
        try{
            String query = "update Album set Title=?, ArtistId=?  where AlbumId=?";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, nuevo.getNombre());
            st.setInt(2, nuevo.getArtista_id());
            st.setInt(3, old.getAlbum_id());
            st.execute();
            return true;
        }
        catch(SQLException ex){
        }

        return false;
    }
    public boolean modifyMedia(MediaType old, MediaType nuevo){
        try{
            String query = "update MediaType set Name=? where MediaTypeId=?";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, nuevo.getName());
            st.setInt(2, old.getMedia_type_id());
            st.execute();
            return true;
        }
        catch(SQLException ex){
        }

        return false;
    }
    public boolean modifyGenre(Genero old, Genero nuevo){
        try{
            String query = "update Genre set Name=? where GenreId=?";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, nuevo.getNombre());
            st.setInt(2, old.getId());
            st.execute();
            return true;
        }
        catch(SQLException ex){
        }

        return false;
    }
    public boolean modifyTrack(Track old, Track nuevo){
        try{
            String query = "UPDATE Track SET Name=?, AlbumId=?, MediaTypeId=?, GenreId=?, Composer=?, Milliseconds=?, Bytes=?, UnitPrice=? WHERE TrackId=?";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, nuevo.getName());
            st.setInt(2, nuevo.getAlbum_id());
            st.setInt(3, nuevo.getMediatype_id());
            st.setInt(4, nuevo.getGenre_id());
            st.setString(5, nuevo.getComposer());
            st.setInt(6, nuevo.getMilliseconds());
            st.setInt(7, nuevo.getBytes());
            st.setDouble(8, nuevo.getUnitPrice());
            st.setInt(9, old.getTrackId());
            st.execute();
            return true;
        }
        catch(SQLException ex){
        }

        return false;
    }
    public boolean artist_has_album(Artista a){

        try{
            String query = "select * from Album where ArtistId=?";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, a.getId());
            ResultSet res=st.executeQuery();

            if(res.next()){
                System.out.println("Si tiene disco");
                return true;
            }

            else{
                System.out.println("No tiene disco");
                return false;
            }
        }
        catch(SQLException ex){}
        return false;
    }
    public boolean album_has_songs(Album a){

        try{
            String query = "select * from Track where AlbumId=?";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, a.getAlbum_id());
            ResultSet res=st.executeQuery();

            if(res.next()){
                System.out.println("Si tiene canciones");
                return true;
            }

            else{
                System.out.println("No tiene canciones");
                return false;
            }
        }
        catch(SQLException ex){}
        return false;
    }
    public boolean customer_has_invoices(Cliente c){

        try{
            String query = "select * from Invoice where CustomerId=?";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, c.getId());
            ResultSet res=st.executeQuery();

            if(res.next()){
                System.out.println("Si tiene facturas");
                return true;
            }

            else{
                System.out.println("No tiene facturas");
                return false;
            }
        }
        catch(SQLException ex){}
        return false;
    }
    public boolean employe_has_customers(Empleado e){

        try{
            String query = "select * from Customer where SupportRepId=?";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, e.getEmp_no());
            ResultSet res=st.executeQuery();

            if(res.next()){
                System.out.println("Si tiene clientes");
                return true;
            }

            else{
                System.out.println("No tiene clientes");
                return false;
            }
        }
        catch(SQLException ex){}
        return false;
    }
    public boolean genre_has_songs(Genero g){

        try{
            String query = "select * from Track where GenreId=?";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, g.getId());
            ResultSet res=st.executeQuery();

            if(res.next()){
                System.out.println("Si es usado");
                return true;
            }

            else{
                System.out.println("No es usado");
                return false;
            }
        }
        catch(SQLException ex){}
        return false;
    }
    public boolean media_is_used(MediaType m){

        try{
            String query = "select * from Track where MediaTypeId=?";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, m.getMedia_type_id());
            ResultSet res=st.executeQuery();

            if(res.next()){
                System.out.println("Si es usado");
                return true;
            }

            else{
                System.out.println("No es usado");
                return false;
            }
        }
        catch(SQLException ex){}
        return false;
    }
    public boolean deleteArtist(Artista a){

        try{
            String query = "delete from Artist where ArtistId=? ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, a.getId());
            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }
    public boolean deleteAlbum(Album a){

        try{
            String query = "delete from Album where AlbumId=? ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, a.getAlbum_id());
            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }
    public boolean deletePlaylist(PlayList a){

        try{
            String query = "delete from Playlist where PlaylistId=? ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, a.getPlaylist_id());
            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }
    public boolean deleteCustomer(Cliente c){

        try{
            String query = "delete from Customer where CustomerId=? ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, c.getId());
            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }
    public boolean deleteEmployee(Empleado e){

        try{
            String query = "delete from Employee where EmployeeId=? ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, e.getEmp_no());
            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }
    public boolean deleteGenre(Genero g){

        try{
            String query = "delete from Genre where GenreId=? ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, g.getId());
            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }
    public boolean deleteMedia(MediaType m){

        try{
            String query = "delete from MediaType where MediaTypeId=? ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, m.getMedia_type_id());
            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }

    public boolean addTrack(Track newTrack){
        try {
            String query = "insert into Track (Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice) values(?,?,?,?,?,?,?,?); ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, newTrack.getName());
            st.setInt(2, newTrack.getAlbum_id());
            st.setInt(3, newTrack.getMediatype_id());
            st.setInt(4, newTrack.getGenre_id());
            st.setString(5, newTrack.getComposer());
            st.setInt(6, newTrack.getMilliseconds());
            st.setInt(7, newTrack.getBytes());
            st.setDouble(8, newTrack.getUnitPrice());

            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;

    }
    public boolean agregarEmpleado(Empleado nuevoEmpleado){
        try {
            String query = "insert into Employee (LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email)  values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = Conexion.prepareStatement(query);

            st.setString(1, nuevoEmpleado.getLast_name());
            st.setString(2, nuevoEmpleado.getFirst_name());
            st.setString(3, nuevoEmpleado.getTitle());
            st.setInt(4, nuevoEmpleado.getReports_to());
            st.setDate(5, nuevoEmpleado.getBirth_date());
            st.setDate(6, nuevoEmpleado.getHire_date());
            st.setString(7, nuevoEmpleado.getAdress());
            st.setString(8, nuevoEmpleado.getCity());
            st.setString(9, nuevoEmpleado.getState());
            st.setString(10, nuevoEmpleado.getCountry());
            st.setString(11, nuevoEmpleado.getPostal_code());
            st.setString(12, nuevoEmpleado.getPhone());
            st.setString(13, nuevoEmpleado.getFax());
            st.setString(14, nuevoEmpleado.getEmail());


            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;

    }
    public boolean deleteEmploye(Empleado empleado){
        try {
            String query = "DELETE FROM Employee WHERE emp_no=? ";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setInt(1, empleado.getEmp_no());
            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public boolean modifyEmploye(Empleado old, Empleado nuevo){

        try {
            String query = "UPDATE Employee SET LastName=?, FirstName=?, Title=?, ReportsTo=?, BirthDate=?, HireDate=?, Address=?, City=?, State=?, Country=?, PostalCode=?, Phone=?, Fax=?, Email=? WHERE EmployeeId=? ;";
            PreparedStatement st = Conexion.prepareStatement(query);
            st.setString(1, nuevo.getLast_name());
            st.setString(2, nuevo.getFirst_name());
            st.setString(3,nuevo.getTitle());
            st.setInt(4, nuevo.getReports_to());
            st.setDate(5, nuevo.getBirth_date());
            st.setDate(6, nuevo.getHire_date());
            st.setString(7, nuevo.getAdress());
            st.setString(8, nuevo.getCity());
            st.setString(9, nuevo.getState());
            st.setString(10, nuevo.getCountry());
            st.setString(11, nuevo.getPostal_code());
            st.setString(12, nuevo.getPhone());
            st.setString(13, nuevo.getFax());
            st.setString(14, nuevo.getEmail());
            st.setInt(15, old.getEmp_no());
            st.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public String getEmpleadosRep(){
        String query = "SELECT e.FirstName as eNombre , e.LastName as eApellido, c.FirstName as cNombre, c.LastName as cApellido FROM Employee e join Customer c on e.EmployeeId = c.SupportRepId";
        String s="\n";
        PreparedStatement st = null;
        try {
            st = Conexion.prepareStatement(query);
            ResultSet res=st.executeQuery();
            while(res.next()){
                s+=res.getString(1)+" "+res.getString(2)+" | "+res.getString(3)+" "+res.getString(4)+"\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    public Connection getConexion(){
        return Conexion;
    }
}
