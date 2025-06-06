package bytetech.movierecmommendations.server.core.client.movie.repository;

import bytetech.movierecmommendations.server.core.admin.movie.model.response.AdminMovieCategoryResponse;
import bytetech.movierecmommendations.server.core.client.movie.model.request.MovieFilterRequest;
import bytetech.movierecmommendations.server.core.client.movie.model.response.UserModifyMovieResponse;
import bytetech.movierecmommendations.server.entities.main.Movie;
import bytetech.movierecmommendations.server.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientMovieRepository extends MovieRepository {

    @Query("SELECT m FROM Movie m " +
            "JOIN MovieCategory mc ON mc.movie = m " +
            "JOIN mc.category c " +
            "WHERE " +
            "m.deleted = false AND " +
            "LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.actor) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(m.year AS string) LIKE %:keyword% OR " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Movie> searchMovies(@Param("keyword") String keyword);

    @Query(value = """
        SELECT m.* FROM movie m
        LEFT JOIN reviewer r ON m.id = r.movie_id
        LEFT JOIN watch_history wh ON m.id = wh.movie_id
        GROUP BY m.id
        ORDER BY
            CASE
                WHEN :sortBy = 'highestRated' THEN AVG(r.rating)
            END DESC,
            CASE
                WHEN :sortBy = 'newest' THEN m.year
            END DESC,
            CASE
                WHEN :sortBy = 'mostPopular' THEN COUNT(wh.movie_id)
            END DESC
        """, nativeQuery = true)
    List<Movie> filterMovies(@Param("sortBy") String sortBy);

    @Query("SELECT m FROM Movie m " +
            "WHERE (:movieId IS NULL OR m.id = :movieId) " +
            "AND m.deleted = false " +
            "ORDER BY m.createdDate DESC")
    Page<Movie> findAllByFilter(@Param("movieId") String movieId, Pageable pageable);

    @Query(value = """
            SELECT DISTINCT
                c.id AS id,
                c.name AS name,
                c.description AS description
            FROM category c
            JOIN movie_category mc on c.id = mc.category_id AND mc.movie_id = :movieId
            WHERE c.deleted = false
            """, nativeQuery = true)
    List<AdminMovieCategoryResponse> findCategoryByMoviesId(String movieId);

    @Query(value = """
            SELECT
                m.id AS id,
                m.title AS title,
                m.description AS description,
                m.picture AS pictureURL,
                m.movies AS movie,
                m.author AS author,
                m.actor AS actor,
                m.year AS year,
                m.created_date AS createdDate,
                m.last_modified_date AS lastModifiedDate
            FROM movie m
            WHERE m.id = :id
            """, nativeQuery = true)
    UserModifyMovieResponse findMovieById(String id);

}
