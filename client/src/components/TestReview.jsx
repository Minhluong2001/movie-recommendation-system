import {useEffect, useState} from "react";
import {UserReviewApi} from "../api/UserReview.ts";
import {FaStar} from "react-icons/fa";
import {toast} from "react-toastify";
import ReactPaginate from "react-paginate";
import {useAuthStore} from "../utils/stores/auth.ts";

export const TestReview = ({movieId}) => {

    const authStore = useAuthStore();
    const user = authStore?.user;
    const userId = authStore?.user?.id;
    const [review, setReview] = useState([]);
    const [page, setPage] = useState(1);
    const [size, setSize] = useState(5);
    const [totalPages, setTotalPages] = useState(1);

    const [newComment, setNewComment] = useState("");
    const [newRating, setNewRating] = useState(5);

    const fetchReview = async () => {
        try {
            const params = {
                movie: movieId,
                page: page,
                size: size,
            }
            const response = await UserReviewApi.getReviews(params);
            console.log("review {}", response);
            const reviews = response?.data?.data || [];
            setTotalPages(response?.data?.totalPages || 1);
            setReview(reviews);
        } catch (error) {
            console.error("Lỗi khi lấy kết quả tìm kiếm:", error);
        }
    };
    useEffect(() => {
        fetchReview();
    }, [page, size]);

    const handleSubmitComment = async () => {
        if (!newComment.trim()) return;

        const data = {
            movieId: movieId,
            rating: newRating,
            comment: newComment,
        }

        try {
            await UserReviewApi.CreateReview(data);

            setNewComment("");
            setNewRating(5);
            fetchReview();
            toast.success("Create success");
        } catch (error) {
            console.error("Lỗi khi gửi bình luận:", error);
        }
    };
    const deleteReview = async (id) => {
        try {
            await UserReviewApi.DeleteReview(id);
            setReview(review.filter((r) => r.id !== id)); // Cập nhật danh sách
        } catch (error) {
            console.error("Lỗi khi xóa đánh giá:", error);
        }
    };
    const handlePageClick = (event) => {
        setPage(event.selected);
    };
    console.log(user)
    return (
        <div className="max-w-4xl mx-auto bg-black text-white p-6 rounded-lg">
            {userId ? <div>
                    <h2 className="text-xl font-semibold mb-4">{user.userName}</h2>

                    <div className="flex flex-col gap-4 mb-6">
                        <div className="flex items-center gap-3">
                            <img
                                src="https://randomuser.me/api/portraits/men/15.jpg"
                                alt="User"
                                className="w-12 h-12 rounded-full"
                            />
                            <input
                                type="text"
                                placeholder="Write your comments here..."
                                className="flex-1 bg-gray-800 text-white px-4 py-2 rounded-lg focus:outline-none"
                                value={newComment}
                                onChange={(e) => setNewComment(e.target.value)}
                            />
                        </div>

                        <div className="flex items-center gap-2">
                            {[...Array(5)].map((_, index) => {
                                const starValue = index + 1;
                                return (
                                    <FaStar
                                        key={index}
                                        className={`cursor-pointer ${
                                            starValue <= newRating ? "text-yellow-400" : "text-gray-500"
                                        }`}
                                        onClick={() => setNewRating(starValue)}
                                    />
                                );
                            })}
                            <span className="ml-2">{newRating} / 5</span>
                        </div>

                        {/* Nút gửi */}
                        <button
                            onClick={handleSubmitComment}
                            className="bg-blue-600 hover:bg-blue-700 px-4 py-2 rounded-lg text-white"
                        >
                            Submit
                        </button>
                    </div>
                </div> :
                <></>
            }


            {review.length > 0 ? (
                <>
                    {review.map((comment) => (
                        <div key={comment.id} className="flex gap-4 mb-6">
                            <img
                                src={`https://randomuser.me/api/portraits/men/${Math.floor(
                                    Math.random() * 100
                                )}.jpg`}
                                alt="User"
                                className="w-12 h-12 rounded-full"
                            />
                            <div>
                                <p className="font-semibold">User ID: {comment?.userId}</p>
                                <p className="text-gray-400 text-sm">
                                    {new Date(comment?.createdDate).toLocaleString()}
                                </p>
                                <div className="flex items-center mt-1">
                                    {[...Array(Number(comment.rating))].map((_, index) => (
                                        <FaStar key={index} className="text-yellow-400 mr-1"/>
                                    ))}
                                </div>
                                <p className="mt-1">{comment.comment}</p>
                                {comment?.userId === userId && (
                                    <button
                                        className="mt-2 text-red-500 hover:text-red-700 text-sm"
                                        onClick={() => deleteReview(comment.id)}
                                    >
                                        Xóa
                                    </button>
                                )}
                            </div>
                        </div>
                    ))}
                    <ReactPaginate
                        previousLabel={"← Previous"}
                        nextLabel={"Next →"}
                        breakLabel={"..."}
                        pageCount={totalPages} // Tổng số trang từ API
                        marginPagesDisplayed={2}
                        pageRangeDisplayed={3}
                        onPageChange={handlePageClick}
                        containerClassName={"flex justify-center mt-4 space-x-2"}
                        pageClassName={"px-3 py-1 border rounded"}
                        activeClassName={"bg-blue-500 text-white"}
                        previousClassName={"px-3 py-1 border rounded"}
                        nextClassName={"px-3 py-1 border rounded"}
                        breakClassName={"px-3 py-1 border rounded"}
                        disabledClassName={"opacity-50 cursor-not-allowed"}
                    />
                </>
            ) : (
                <p className="text-gray-400">No comments yet.</p>
            )}
        </div>
    );
};
