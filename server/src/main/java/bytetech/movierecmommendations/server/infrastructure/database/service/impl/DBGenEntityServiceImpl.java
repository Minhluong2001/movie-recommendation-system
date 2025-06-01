package bytetech.movierecmommendations.server.infrastructure.database.service.impl;

import bytetech.movierecmommendations.server.entities.main.*;
import bytetech.movierecmommendations.server.infrastructure.constants.module.RoleConstant;
import bytetech.movierecmommendations.server.infrastructure.database.repository.*;
import bytetech.movierecmommendations.server.infrastructure.database.service.DBGenEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class DBGenEntityServiceImpl implements DBGenEntityService {

    private final DBGenUserRepository userRepository;
    private final DBGenCategoryRepository categoryRepository;
    private final DBGenMovieRepository movieRepository;
    private final DBGenReviewRepository reviewRepository;
    private final DBGenMovieCategoryRepository movieCategoryRepository;

    public DBGenEntityServiceImpl(DBGenUserRepository userRepository, DBGenCategoryRepository categoryRepository,
                                  DBGenMovieRepository movieRepository, DBGenReviewRepository reviewRepository, DBGenMovieCategoryRepository movieCategoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
        this.movieCategoryRepository = movieCategoryRepository;
    }

    @Override
    public void generateUser() {
        if (userRepository.count() == 0) {
            List<User> users = List.of(
                    new User("Minh Lương", "0849070511", "minhluong@gmail.com", "12345678", "Hà Nội", "https://avatars.githubusercontent.com/u/171242028?v=4", RoleConstant.ADMIN),
                    new User("Trần Văn B", "0849070512", "vanb@gmail.com", "12345678", "TP.HCM", null, RoleConstant.USER),
                    new User("Lê Thị C", "0849070513", "letc@gmail.com", "12345678", "Đà Nẵng", null, RoleConstant.USER),
                    new User("Phạm Văn D", "0849070514", "pvd@gmail.com", "12345678", "Hải Phòng", null, RoleConstant.USER),
                    new User("Hoàng Huy Hiệu", "0849070515", "hieuhoang@gmail.com", "12345678", "Nha Trang", null, RoleConstant.ADMIN),
                    new User("Trịnh Hiếu Nghĩa", "0849070516", "nghiabe.dev@gmail.com", "12345678", "Hà Nội", null, RoleConstant.ADMIN),
                    new User("Nguyễn Phúc Công", "0849070517", "abc@gmail.com", "12345678", "Huế", null, RoleConstant.ADMIN),
                    new User("Lý Văn E", "0849070518", "lyve@gmail.com", "12345678", "Vũng Tàu", null, RoleConstant.USER),
                    new User("Đặng Thị F", "0849070519", "dtf@gmail.com", "12345678", "Hải Dương", null, RoleConstant.USER),
                    new User("Vũ Minh G", "0849070520", "vmg@gmail.com", "12345678", "Cần Thơ", null, RoleConstant.USER),
                    new User("Phan Văn H", "0849070521", "pvh@gmail.com", "12345678", "Bắc Giang", null, RoleConstant.USER),
                    new User("Nguyễn Văn I", "0849070522", "nvi@gmail.com", "12345678", "Hòa Bình", null, RoleConstant.USER),
                    new User("Lê Thị J", "0849070523", "ltj@gmail.com", "12345678", "Quảng Ninh", null, RoleConstant.USER),
                    new User("Trần Văn K", "0849070524", "tvk@gmail.com", "12345678", "Đồng Nai", null, RoleConstant.USER),
                    new User("Phạm Văn L", "0849070525", "pvl@gmail.com", "12345678", "Lâm Đồng", null, RoleConstant.USER),
                    new User("Lê Văn M", "0849070526", "lvm@gmail.com", "12345678", "Bến Tre", null, RoleConstant.USER),
                    new User("Đinh Văn N", "0849070527", "dvn@gmail.com", "12345678", "Bình Định", null, RoleConstant.USER),
                    new User("Nguyễn Thị O", "0849070528", "nto@gmail.com", "12345678", "Phú Yên", null, RoleConstant.USER),
                    new User("Hoàng Văn P", "0849070529", "hvp@gmail.com", "12345678", "Tây Ninh", null, RoleConstant.USER),
                    new User("Trần Văn Q", "0849070530", "tvq@gmail.com", "12345678", "An Giang", null, RoleConstant.USER)

            );
            userRepository.saveAll(users);
        }
    }

    @Override
    public void generateCategory() {
        if (categoryRepository.count() == 0) {
            List<Category> categories = List.of(
                    new Category("Action", "Hành động"),
                    new Category("Comedy", "Hài hước"),
                    new Category("Drama", "Chính kịch"),
                    new Category("Horror", "Kinh dị"),
                    new Category("Sci-Fi", "Khoa học viễn tưởng"),
                    new Category("Romance", "Lãng mạn"),
                    new Category("Fantasy", "Giả tưởng"),
                    new Category("Thriller", "Giật gân"),
                    new Category("Adventure Epic", "Cuộc phiêu lưu sử thi"),
                    new Category("Epic", "Sử thi"),
                    new Category("Space Sci-Fi", "Khoa học viễn tưởng vũ trụ"),
                    new Category("Mystery", "Bí ẩn"),
                    new Category("Time Travel", "Du hành thời gian"),
                    new Category("Animation", "Hoạt hình"),
                    new Category("Social Psychology", "Phim Tâm Lý Xã Hội"),
                    new Category("CateMana2", "Convert to Basic Latin"),
                    new Category("Coming-of-age", "sự trưởng thành"),
                    new Category("Costume Drama", "Phim cổ trang"),
                    new Category("Holiday Romance", "Kỳ nghỉ lãng mạn"),
                    new Category("Romantic Comedy", "Hài kịch lãng mạn"),
                    new Category("Tragic Romance", "Lãng mạn bi kịch"),
                    new Category("Steamy Romance", "Lãng mạn nóng bỏng")
            );
            categoryRepository.saveAll(categories);
        }
    }

    @Override
    public void generateMovie() {
        if (movieRepository.count() == 0) {
            List<Movie> movies = List.of(
                    new Movie(
                            "The Ancestral House",
                            "The film revolves around the story of generations in a family, focusing on the two main characters, Gia Minh and My Tien, as they explore traditional values and family relationships.",
                            "Huynh Lap",
                            "Huynh Lap, Phuong My Chi, NSUT Hanh Thuy, NSUT Huynh Dong",
                            2025,
                            "https://cdn.galaxycine.vn/media/2025/2/17/nha-gia-tien-750_1739775155248.jpg",
                            "https://www.youtube.com/watch?v=hXGozmNBwt4&t=1s"
                    ),
                    new Movie(
                            "Linh Mieu: The Possessed Cat",
                            "The story of a mystical cat with the power to resurrect the dead, causing a series of horrifying events in a small village.",
                            "Nguyen Huu Hoang",
                            "Tran Nghia, Hoang Yen Chibi, NSUT Huu Chau",
                            2024,
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNyqzAbIYFcL7lrZ7Va6aukS5YygRFddU_VQ&s",
                            "https://www.youtube.com/watch?v=XsPl7SbL2kg"
                    ),
                    new Movie(
                            "Ma Da",
                            "A group of young friends explores a desolate land and accidentally awakens the spirit of Ma Da, leading to terrifying encounters.",
                            "Le Van Kiet",
                            "Ngo Thanh Van, Jun Pham, Kha Ngan",
                            2024,
                            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRpYARBIdLAF9zaEo7CxcofAXy717mtOxsSW9F3qaDZrXkQq4RB",
                            "https://www.youtube.com/watch?v=vC-KNlLNIso"
                    ),
                    new Movie(
                            "The Dark Knight",
                            "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.",
                            "Christopher Nolan",
                            "Christian Bale, Heath Ledger, Aaron Eckhart",
                            2008,
                            "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=EXeTwQWrcwY"
                    ),
                    new Movie(
                            "Inception",
                            "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
                            "Christopher Nolan",
                            "Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page",
                            2010,
                            "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=YoHD9XEInc0"
                    ),
                    new Movie(
                            "The Matrix",
                            "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
                            "Lana Wachowski, Lilly Wachowski",
                            "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss",
                            1999,
                            "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=vKQi3bBA1y8"
                    ),
                    new Movie(
                            "Interstellar",
                            "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.",
                            "Christopher Nolan",
                            "Matthew McConaughey, Anne Hathaway, Jessica Chastain",
                            2014,
                            "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=zSWdZVtXT7E"
                    ),
                    new Movie(
                            "The Lord of the Rings: The Fellowship of the Ring",
                            "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
                            "Peter Jackson",
                            "Elijah Wood, Ian McKellen, Orlando Bloom",
                            2001,
                            "https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=V75dMMIW2B4"
                    ),
                    new Movie(
                            "Avengers: Endgame",
                            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
                            "Anthony Russo, Joe Russo",
                            "Robert Downey Jr., Chris Evans, Mark Ruffalo",
                            2019,
                            "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=TcMBFSGVi1c"
                    ),
                    new Movie(
                            "The Shawshank Redemption",
                            "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                            "Frank Darabont",
                            "Tim Robbins, Morgan Freeman, Bob Gunton",
                            1994,
                            "https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=6hB3S9bIaco"
                    ),
                    new Movie(
                            "Pulp Fiction",
                            "The lives of two mob hitmen, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
                            "Quentin Tarantino",
                            "John Travolta, Uma Thurman, Samuel L. Jackson",
                            1994,
                            "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=s7EdQ4FqbhY"
                    ),
                    new Movie(
                            "The Godfather",
                            "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                            "Francis Ford Coppola",
                            "Marlon Brando, Al Pacino, James Caan",
                            1972,
                            "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=sY1S34973zA"
                    ),
                    new Movie(
                            "Forrest Gump",
                            "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other historical events unfold through the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.",
                            "Robert Zemeckis",
                            "Tom Hanks, Robin Wright, Gary Sinise",
                            1994,
                            "https://m.media-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=bLvqoHBptjg"
                    ), new Movie(
                            "Oppenheimer",
                            "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.",
                            "Christopher Nolan",
                            "Cillian Murphy, Emily Blunt, Matt Damon",
                            2023,
                            "https://m.media-amazon.com/images/M/MV5BMDBmYTZjNjUtN2M1MS00MTQ2LTk2ODgtNzc2M2QyZGE5NTVjXkEyXkFqcGdeQXVyNzAwMjU2MTY@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=uYPbbksJxIg"
                    ),
                    new Movie(
                            "Barbie",
                            "Barbie suffers a crisis that leads her to question her world and her existence.",
                            "Greta Gerwig",
                            "Margot Robbie, Ryan Gosling, America Ferrera",
                            2023,
                            "https://m.media-amazon.com/images/M/MV5BNjU3N2QxNzYtMjk1NC00MTc4LTk1NTQtMmUxNTljM2I0NDA5XkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=pBk4NYhWNMM"
                    ),
                    new Movie(
                            "Get Out",
                            "A young African-American man visits his white girlfriend's family estate and uncovers a disturbing secret.",
                            "Jordan Peele",
                            "Daniel Kaluuya, Allison Williams, Bradley Whitford, Catherine Keener",
                            2017,
                            "https://upload.wikimedia.org/wikipedia/en/a/a3/Get_Out_poster.png",
                            "https://www.youtube.com/watch?v=DzfpyUB60YY"
                    ),
                    new Movie(
                            "Getting Rich with Ghosts",
                            "The story of a man seeking quick wealth by making a pact with supernatural forces, but he soon faces dire consequences.",
                            "Phan Gia Nhat Linh",
                            "Tran Thanh, Lan Ngoc, Tien Luat",
                            2024,
                            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcR7rYDstB2Tox9_LxIESL-lVAiwAmV5dHJ4s6qE9ANXuncJfc8h",
                            "https://www.youtube.com/watch?v=555oiY9RWM4"
                    ),
                    new Movie(
                            "To All the Boys I've Loved Before 1",
                            "A teenage girl's secret love letters are exposed and wreak havoc on her love life.",
                            "Susan Johnson",
                            " Lana Condor, Noah Centineo, Janel Parrish",
                            2018,
                            "https://ntvb.tmsimg.com/assets/p15663025_v_h10_aa.jpg?w=1280&h=720",
                            "https://www.youtube.com/watch?v=MtZ_hf7tLxk"
                    ),
                    new Movie(
                            "Call me by your name",
                            "It's the summer of 1983, and 17-year-old Elio is spending the days with his family at their villa in Lombardy, Italy. He soon meets Oliver, who's working as an intern for Elio's father. They discover the heady beauty of awakening desire.",
                            "Luca Guadagnino",
                            "Timothée Chalamet, Armie Hammer, Michael Stuhlbarg",
                            2017,
                            "https://m.media-amazon.com/images/M/MV5BNDk3NTEwNjc0MV5BMl5BanBnXkFtZTgwNzYxNTMwMzI@._V1_.jpg",
                            "https://www.youtube.com/watch?v=Z9AYPxH5NTM&pp=0gcJCdgAo7VqN5tD"
                    ),
                    new Movie(
                            "Little Women",
                            "In 19th century Massachusetts, the March sisters--Meg, Jo, Beth, and Amy--on the threshold of womanhood, go through many ups and downs in life and endeavor to make important decisions about their futures.",
                            "Greta Gerwig",
                            "Saoirse Ronan, Emma Watson, Florence Pugh",
                            2019,
                            "https://upload.wikimedia.org/wikipedia/en/9/9d/Little_Women_%282019_film%29.jpeg",
                            "https://www.youtube.com/watch?v=AST2-4db4ic"
                    ),
                    new Movie(
                            "After We Collided",
                            "Based on the 2014 romance novel of the same name, this follows the love life of two young adults.",
                            "Roger Kumble",
                            "Josephine Langford, Hero Fiennes Tiffin, Dylan Sprouse",
                            2020,
                            "https://m.media-amazon.com/images/M/MV5BMDFmMGYzMGYtNWFkNy00NTFmLWIzNjMtNjhjOWI3NjEyNDE0XkEyXkFqcGc@._V1_.jpg",
                            "https://www.youtube.com/watch?v=2SvwX3ux_-8"
                    ),
                    new Movie(
                            "Beautiful Disaster",
                            "College freshman Abby tries to distance herself from her dark past while resisting her attraction to bad boy Travis.\n",
                            "Roger Kumble",
                            "Dylan Sprouse, Virginia Gardner, Austin North",
                            2023,
                            "https://upload.wikimedia.org/wikipedia/en/9/94/Beautiful_Disaster.jpeg",
                            "https://www.youtube.com/watch?v=EMK3wy2563g"
                    ),
                    new Movie(
                            "After Ever Happy",
                            "As a shocking truth about a couple's families emerges, the two lovers discover they are not so different from each other.\n",
                            "Castille Landon",
                            "Josephine Langford, Hero Fiennes Tiffin, Louise Lombard",
                            2022,
                            "https://upload.wikimedia.org/wikipedia/en/thumb/3/3e/After_Ever_Happy_official_poster.png/250px-After_Ever_Happy_official_poster.png",
                            "https://www.youtube.com/watch?v=hLQ-5exgctI"
                    ),
                    new Movie(
                            "The Platform 2",
                            "A thrilling physical journey that allows an approach to the darkness, where it is scary to look. It appeals to the viewer's civil responsibility and forces them to face the limits of their own solidarity.\n",
                            "Galder Gaztelu-Urrutia",
                            "Milena Smit, Hovik Keuchkerian, Natalia Tena",
                            2024,
                            "https://m.media-amazon.com/images/M/MV5BNzI4YjkyODctMGJmNC00YjBjLTllNjAtNDkxNTJmZjg4MGZkXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg",
                            "https://www.youtube.com/watch?v=UKFMYWNatQM"
                    ),
                    new Movie(
                            "Stranger things season 4",
                            "Mysteries unravel in a small Midwestern town in the 1980s, involving supernatural forces, secret experiments and one strange girl.",
                            "Matt Duffer, Ross Duffer",
                            "Millie Bobby Brown, Finn Wolfhard, Winona Ryder",
                            2022,
                            "https://upload.wikimedia.org/wikipedia/en/7/78/Stranger_Things_season_4.jpg",
                            "https://www.youtube.com/watch?v=sBEvEcpnG7k"
                    ),
                    new Movie(
                            "Dune: Part Two",
                            "Paul Atreides unites with the Fremen while on a warpath of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the universe, he endeavors to prevent a terrible future.",
                            "Denis Villeneuve",
                            "Timothée Chalamet, Zendaya, Rebecca Ferguson",
                            2024,
                            "https://upload.wikimedia.org/wikipedia/vi/9/94/Dune_2_VN_poster.jpg",
                            "https://www.youtube.com/watch?v=kCO-RO3q7U4"
                    ),
                    new Movie(
                            "Reply 1988",
                            "Five childhood friends, who all live in the same Ssangmundong neighborhood of Seoul in 1988, lean on each other to survive their challenging teen years and set a path for their futures.",
                            "Shin Won-ho",
                            "Lee Hyeri, Park Bo-gum, Ryu Jun-yeol",
                            2015,
                            "https://occ-0-8407-90.1.nflxso.net/dnm/api/v6/E8vDc_W8CLv7-yMQu8KMEC7Rrr8/AAAABQiLySv67Z00six6BhU4PgKg_NXjsV_7gyzO_2FklWxZfb3-ZTCvYbGG8oH3WcDKvMdR3gibmujM2Kp7Wm25ubT4AdlKuHMCSyYj.jpg?r=751",
                            "https://www.youtube.com/watch?v=c-EMf3JdxUA"
                    ),
                    new Movie(
                            "Lupin",
                            "Inspired by the adventures of Arsène Lupin, gentleman thief Assane Diop sets out to avenge his father for an injustice inflicted by a wealthy family.\n",
                            "George Kay",
                            "Omar Sy, Ludivine Sagnier, Soufiane Guerrab",
                            2021,
                            "https://m.media-amazon.com/images/M/MV5BMDJhODQ3ZDEtN2JmMS00Yjk5LTk1ZTUtMzI3YTU0NTNjMjAyXkEyXkFqcGc@._V1_.jpg",
                            "https://www.youtube.com/watch?v=ga0iTWXCGa0"
                    ),
                    new Movie(
                            "Come Home, My Children",
                            "The story follows Mr. Son, a single father raising his three daughters—Hue, Thu, and Duong—through the ups and downs of life, highlighting the boundless love within a family.",
                            "Nguyen Danh Dung",
                            "NSND Trung Anh, Bao Thanh, Thu Quynh, Bao Han",
                            2019,
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRaa9vLk0VbyXkfr4Mj4KineY_Jp_TDLffHCw&s",
                            "https://www.youtube.com/watch?v=r-ZM97E869w"
                    )
            );
            movieRepository.saveAll(movies);
        }
    }

    @Override
    public void generateReview() {
        List<User> users = userRepository.findAll();
        List<Movie> movies = movieRepository.findAll();

        if (users.isEmpty() || movies.isEmpty()) {
            log.error("Không thể tạo review vì user hoặc movie chưa có trong database.");
            return;
        }

        if (reviewRepository.count() == 0) {
            List<Reviewer> reviews = IntStream.rangeClosed(1, 3)
                    .mapToObj(i -> new Reviewer(
                            users.get(new Random().nextInt(users.size())),
                            movies.get(new Random().nextInt(movies.size())),
                            "Review content " + i,
                            1 + new Random().nextInt(5)
                    ))
                    .collect(Collectors.toList());
            reviewRepository.saveAll(reviews);
        }
    }

    @Override
    public void generateMovieCategory() {
        List<Movie> movies = movieRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty() || movies.isEmpty()) {
            log.error("Không thể tạo movie category vì danh sách phim hoặc thể loại rỗng.");
            return;
        }

        if (movieCategoryRepository.count() == 0) {
            List<MovieCategory> movieCategories = new ArrayList<>();
            Random random = new Random();

            for (Movie movie : movies) {
                Category randomCategory = categories.get(random.nextInt(categories.size()));
                movieCategories.add(new MovieCategory(movie, randomCategory));

                int additionalCategories = random.nextInt(2);
                for (int i = 0; i < additionalCategories; i++) {
                    Category additionalCategory = categories.get(random.nextInt(categories.size()));
                    if (movieCategories.stream().noneMatch(mc -> mc.getMovie().equals(movie) && mc.getCategory().equals(additionalCategory))) {
                        movieCategories.add(new MovieCategory(movie, additionalCategory));
                    }
                }
            }

            movieCategoryRepository.saveAll(movieCategories);
            log.info("Đã tạo {} movie categories.", movieCategories.size());
        }
    }

}