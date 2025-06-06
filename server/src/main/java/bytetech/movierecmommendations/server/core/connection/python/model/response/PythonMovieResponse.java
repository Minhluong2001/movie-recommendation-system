package bytetech.movierecmommendations.server.core.connection.python.model.response;

import bytetech.movierecmommendations.server.core.admin.movie.model.response.AdminMovieCategoryResponse;

import java.util.List;

public interface PythonMovieResponse {

    String getId();

    String getTitle();

    String getDescription();

    String getPictureURL();

    String getMovieURL();

    String getAuthor();

    String getActor();

    Integer getYear();

    Long getCreatedDate();

    Long getLastModifiedDate();

    Double getRating();

}
