import PropTypes from "prop-types";
import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";
import {useNavigate} from "react-router-dom";
import testImage from "../assets/img/testimg.jpg";
import {UserMovieApi} from "../api/UserMovie";

const responsive = {
    superLargeDesktop: {
        breakpoint: {max: 4000, min: 3000},
        items: 10,
    },
    desktop: {
        breakpoint: {max: 3000, min: 1200},
        items: 7,
    },
    tablet: {
        breakpoint: {max: 1200, min: 600},
        items: 3,
    },
    mobile: {
        breakpoint: {max: 600, min: 0},
        items: 2,
    },
};

const MovieListCus = ({title, data}) => {
    const navigate = useNavigate();

    const handleNavigate = async (movie) => {
        await UserMovieApi.ViewMovie(movie);
        navigate(`/detail/${movie.id}`);
    }

    return (
        <div className="my-10 px-10 max-w-full">
            <h2 className="text-xl uppercase mb-4">{title}</h2>
            <Carousel responsive={responsive} draggable={false}>
                {data?.map((movie) => (
                    <div
                        key={movie.id}
                        className="bg-cover bg-no-repeat bg-center w-[200px] h-[300px] relative hover:scale-110 transition-transform duration-500 ease-in-out cursor-pointer"
                        style={{
                            backgroundImage: `url(${movie.pictureURL || testImage})`,
                        }}
                        onClick={() => handleNavigate(movie)}
                    >
                        <div
                            className="absolute top-2 left-2 w-10 h-10 bg-red-600 text-white font-bold flex items-center justify-center rounded-full shadow-lg">
                            {movie.rating?.toFixed(1)}
                        </div>
                        <div className="bg-black w-full h-full opacity-40 absolute top-0 left-0 z-0"/>
                        <div className="relative p-4 flex flex-col items-center justify-end h-full">
                            <h3 className="text-md uppercase text-center">{movie.title}</h3>
                        </div>
                    </div>
                ))}
            </Carousel>
        </div>
    );
};

MovieListCus.propTypes = {
    title: PropTypes.string.isRequired,
    data: PropTypes.array,
};

export default MovieListCus;
