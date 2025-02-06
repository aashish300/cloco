package artist_management_system.java.ApiConstant;

public interface ApiConstant {

    String API = "/api";
    String USER = API + "/user";
    String ARTIST = API + "/artist";
    String MUSIC = API + "/music";

    // Common
    String FINDALL = "/findAll";
    String ID = "/{id}";
    String SAVE = "/save";
    String UPDATE = "/update";
    String FINDALLBYPAGINATION = "/findAllByPagination";
    String MUSICBYARTISTID = "/findMusicByArtistId" + ID;


    String CSV_UPLOAD = "/csv/upload";
    String CSV_EXPORT = "/csv/export";

    String LOGIN = "/login";
    String REGISTER = "/register";

}
