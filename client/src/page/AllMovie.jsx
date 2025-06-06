import {useEffect, useState} from "react";
import Filter from "../components/Filter";
import SortDropdown from "../components/SortDropdown";
import {FaFilter} from "react-icons/fa";
import testImage from "../assets/img/testimg.jpg";
import ReactPaginate from "react-paginate";
import {GrFormNext, GrFormPrevious} from "react-icons/gr";
import {useNavigate} from "react-router-dom";
import {UserMovieApi} from "../api/UserMovie.ts";

const AllMovie = () => {
    const navigate = useNavigate();

    const [isFilterVisible, setIsFilterVisible] = useState(false);
    const [movies, setMovies] = useState([]);
    const [totalPages, setTotalPages] = useState(1);
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(10);
    const [sortId, setSortId] = useState(null);

    useEffect(() => {
        const fetchMovies = async () => {
            try {
                const params = {
                    page: page, size: size,
                }
                const response = await UserMovieApi.GetMovies(params);
                console.log("response", response);
                setMovies(response.data.content || []);
                setTotalPages(response.data.totalPages || 1);
            } catch (error) {
                console.error("Lỗi khi lấy dữ liệu:", error);
            }
        };

        fetchMovies();
    }, [page, size]);

    const handlePageClick = (event) => {
        setPage(event.selected);
    };

    const fetchSortedData = async (sortId) => {
        try {
            const params = {
                sortBy: sortId,
            }
            const response = await UserMovieApi.GetMovies(params);
            setMovies(response?.data?.content || []);
            setTotalPages(Math.ceil(response?.data?.content?.length / size));
        } catch (error) {
            console.error("Lỗi khi lấy dữ liệu:", error);
        }
    };

    const handleSortChange = (sortId) => {
        setSortId(sortId);
        fetchSortedData(sortId);
    };

    const handleNavigate = async (movie) => {
        await UserMovieApi.ViewMovie(movie);
        navigate(`/detail/${movie.id}`);
    }

    return (<div className="min-h-screen bg-black text-white  pb-10 pt-20">
        <div className="my-10 px-10 max-w-full ">
            <h2 className="text-xl uppercase mb-4 mt-10">Movies</h2>
            <div className="flex items-center space-x-4">
                <SortDropdown onSortChange={handleSortChange}/>
            </div>

            <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-4 mt-6 ">
                {movies.map((movie) => (<div
                    key={movie.id}
                    className="bg-cover bg-no-repeat bg-center w-[200px] h-[300px] relative hover:scale-110 transition-transform duration-500 ease-in-out cursor-pointer"
                    style={{
                        backgroundImage: `url(${movie.picture || testImage})`,
                    }}
                    onClick={() => handleNavigate(movie)}
                >
                    <div className="bg-black w-full h-full opacity-40 absolute top-0 left-0 z-0"/>
                    <div className="relative p-4 flex flex-col items-center justify-end h-full">
                        <h3 className="text-md uppercase">{movie.title}</h3>
                    </div>
                </div>))}
            </div>
            <div className="flex justify-center mt-10">
                <ReactPaginate
                    previousLabel={<GrFormPrevious/>}
                    nextLabel={<GrFormNext/>}
                    breakLabel={"..."}
                    pageCount={totalPages}
                    marginPagesDisplayed={1}
                    pageRangeDisplayed={3}
                    onPageChange={handlePageClick}
                    // onPageChange={sortId ? handleNoPageClick : handlePageClick}
                    containerClassName="pagination flex items-center gap-2"
                    pageClassName="px-4 py-2 rounded-md bg-[#171923] text-white transition-all hover:bg-[#24262F]"
                    activeClassName="border border-[#A78BFA] text-[#A78BFA]"
                    previousClassName="px-4 py-3 rounded-md bg-[#171923] text-white hover:bg-[#24262F]" // Tăng padding
                    nextClassName="px-4 py-3 rounded-md bg-[#171923] text-white hover:bg-[#24262F]" // Tăng padding
                    disabledClassName="opacity-50 cursor-not-allowed"
                />
            </div>
        </div>
    </div>);
};

export default AllMovie;
