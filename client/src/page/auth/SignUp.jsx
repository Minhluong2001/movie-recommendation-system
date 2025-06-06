import React, {useState} from "react";
import {FcGoogle} from "react-icons/fc";
import * as yup from "yup";
import {yupResolver} from "@hookform/resolvers/yup";
import {useForm} from "react-hook-form";
import AuthApi from "../../api/auth.ts";
import {toast} from "react-toastify";
import {URL_FRONTEND} from "../../utils/constants/url";

const schema = yup.object().shape({
    fullName: yup
        .string()
        .min(3, "Tên phải có ít nhất 3 ký tự")
        .required("Tên người dùng là bắt buộc"),
    email: yup.string().email("Email không hợp lệ").required("Email là bắt buộc"),
    password: yup
        .string()
        .min(6, "Mật khẩu ít nhất 6 ký tự")
        .required("Mật khẩu là bắt buộc"),
});

const SignUp = () => {

    const {
        register,
        handleSubmit,
        formState: {errors},
    } = useForm({
        resolver: yupResolver(schema),
    });

    const onSubmit = async (data) => {
        try {
            const res = await AuthApi.registerAccount(data);
            if (res.status === "OK") {
                toast.success("Chuyển hướng đăng nhập");
                const res2 = await AuthApi.login(data);
                if (res2?.data) {
                    window.location.href = `${URL_FRONTEND}?state=${res2.data}`;
                }
                if (res2?.data?.message) {
                    toast.warning(res2?.data?.message);
                }
            } else {
                toast.error(res.message);
            }
        } catch (error) {
            toast.error(error.response.data.message);
        }
    };

    const [showPass, setShowPass] = useState(false);
    const [rememberMe, setRememberMe] = useState(false);
    const togglePass = () => {
        setShowPass(!showPass);
    };

    return (
        <div className="font-[sans-serif] bg-white">
            <div className="min-h-screen flex fle-col items-center justify-center py-6 px-4">
                <div className="grid md:grid-cols-2 items-center gap-4 max-w-6xl w-full">
                    <div
                        className="border border-gray-300 rounded-lg p-6 max-w-md shadow-[0_2px_22px_-4px_rgba(93,96,127,0.2)] max-md:mx-auto">
                        <form className="space-y-4" onSubmit={handleSubmit(onSubmit)}>
                            <div className="mb-8">
                                <h3 className="text-gray-800 text-3xl font-extrabold">
                                    Sign Up
                                </h3>
                            </div>

                            <div>
                                <label className="text-gray-800 text-sm mb-2 block text-left">
                                    Email
                                </label>
                                <div className="relative flex items-center">
                                    <input
                                        // name="email"
                                        {...register("email")}
                                        type="text"
                                        className="w-full text-sm text-gray-800 border border-gray-300 px-4 py-3 rounded-lg outline-blue-600"
                                        placeholder="Enter user name"
                                    />
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        fill="#bbb"
                                        stroke="#bbb"
                                        className="w-[18px] h-[18px] absolute right-4"
                                        viewBox="0 0 24 24"
                                    >
                                        <circle
                                            cx="10"
                                            cy="7"
                                            r="6"
                                            data-original="#000000"
                                        ></circle>
                                        <path
                                            d="M14 15H6a5 5 0 0 0-5 5 3 3 0 0 0 3 3h12a3 3 0 0 0 3-3 5 5 0 0 0-5-5zm8-4h-2.59l.3-.29a1 1 0 0 0-1.42-1.42l-2 2a1 1 0 0 0 0 1.42l2 2a1 1 0 0 0 1.42 0 1 1 0 0 0 0-1.42l-.3-.29H22a1 1 0 0 0 0-2z"
                                            data-original="#000000"
                                        ></path>
                                    </svg>
                                </div>
                                {errors.email && (
                                    <p className="text-red-500 text-xs mt-1">
                                        {errors.email.message}
                                    </p>
                                )}
                            </div>
                            <div>
                                <label className="text-gray-800 text-sm mb-2 block text-left">
                                    UserName
                                </label>
                                <div className="relative flex items-center">
                                    <input
                                        // name="email"
                                        {...register("fullName")}
                                        type="text"
                                        className="w-full text-sm text-gray-800 border border-gray-300 px-4 py-3 rounded-lg outline-blue-600"
                                        placeholder="Enter user name"
                                    />
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        fill="#bbb"
                                        stroke="#bbb"
                                        className="w-[18px] h-[18px] absolute right-4"
                                        viewBox="0 0 24 24"
                                    >
                                        <circle
                                            cx="10"
                                            cy="7"
                                            r="6"
                                            data-original="#000000"
                                        ></circle>
                                        <path
                                            d="M14 15H6a5 5 0 0 0-5 5 3 3 0 0 0 3 3h12a3 3 0 0 0 3-3 5 5 0 0 0-5-5zm8-4h-2.59l.3-.29a1 1 0 0 0-1.42-1.42l-2 2a1 1 0 0 0 0 1.42l2 2a1 1 0 0 0 1.42 0 1 1 0 0 0 0-1.42l-.3-.29H22a1 1 0 0 0 0-2z"
                                            data-original="#000000"
                                        ></path>
                                    </svg>
                                </div>
                                {errors.fullName && (
                                    <p className="text-red-500 text-xs mt-1">
                                        {errors.fullName.message}
                                    </p>
                                )}
                            </div>
                            <div>
                                <label className="text-gray-800 text-sm mb-2 block text-left">
                                    Password
                                </label>
                                <div className="relative flex items-center">
                                    <input
                                        // name="password"
                                        {...register("password")}
                                        type={showPass ? "text" : "password"}
                                        className="w-full text-sm text-gray-800 border border-gray-300 px-4 py-3 rounded-lg outline-blue-600"
                                        placeholder="Enter password"
                                    />
                                    {showPass ? (
                                        <svg
                                            xmlns="http://www.w3.org/2000/svg"
                                            fill="#bbb"
                                            stroke="#bbb"
                                            className="w-[18px] h-[18px] absolute right-4 cursor-pointer"
                                            viewBox="0 0 128 128"
                                            onClick={togglePass}
                                        >
                                            <path
                                                d="M64 104C22.127 104 1.367 67.496.504 65.943a4 4 0 0 1 0-3.887C1.367 60.504 22.127 24 64 24s62.633 36.504 63.496 38.057a4 4 0 0 1 0 3.887C126.633 67.496 105.873 104 64 104zM8.707 63.994C13.465 71.205 32.146 96 64 96c31.955 0 50.553-24.775 55.293-31.994C114.535 56.795 95.854 32 64 32 32.045 32 13.447 56.775 8.707 63.994zM64 88c-13.234 0-24-10.766-24-24s10.766-24 24-24 24 10.766 24 24-10.766 24-24 24zm0-40c-8.822 0-16 7.178-16 16s7.178 16 16 16 16-7.178 16-16-7.178-16-16-16z"
                                                data-original="#000000"
                                            ></path>
                                            <path d="M16 16L112 112" stroke="#bbb" strokeWidth="8"/>
                                        </svg>
                                    ) : (
                                        <svg
                                            xmlns="http://www.w3.org/2000/svg"
                                            fill="#bbb"
                                            stroke="#bbb"
                                            className="w-[18px] h-[18px] absolute right-4 cursor-pointer"
                                            viewBox="0 0 128 128"
                                            onClick={togglePass}
                                        >
                                            <path
                                                d="M64 104C22.127 104 1.367 67.496.504 65.943a4 4 0 0 1 0-3.887C1.367 60.504 22.127 24 64 24s62.633 36.504 63.496 38.057a4 4 0 0 1 0 3.887C126.633 67.496 105.873 104 64 104zM8.707 63.994C13.465 71.205 32.146 96 64 96c31.955 0 50.553-24.775 55.293-31.994C114.535 56.795 95.854 32 64 32 32.045 32 13.447 56.775 8.707 63.994zM64 88c-13.234 0-24-10.766-24-24s10.766-24 24-24 24 10.766 24 24-10.766 24-24 24zm0-40c-8.822 0-16 7.178-16 16s7.178 16 16 16 16-7.178 16-16-7.178-16-16-16z"
                                                data-original="#000000"
                                            ></path>
                                        </svg>
                                    )}
                                </div>
                                {errors.password && (
                                    <p className="text-red-500 text-xs mt-1">
                                        {errors.password.message}
                                    </p>
                                )}
                            </div>
                            <div className="!mt-8">
                                <button
                                    type="submit"
                                    className="w-full shadow-xl py-3 px-4 text-sm tracking-wide rounded-lg text-white bg-blue-600 hover:bg-blue-700 focus:outline-none"
                                >
                                    Sign Up
                                </button>
                                <span className="flex items-center justify-center w-full m-2 ">
                                  or
                                </span>
                                <button
                                    className="flex items-center justify-center w-full border border-gray-300 rounded-lg py-2 px-4 text-gray-700 hover:bg-gray-100">
                                    <FcGoogle className="w-6 h-6 mr-2"/>
                                    <span>Login with Google</span>
                                </button>
                            </div>
                        </form>
                        <a
                            href={'/login'}
                            className="block text-sm text-gray-800 mt-2 cursor-pointer"
                        >
                            Login now
                        </a>
                    </div>
                    <div className="lg:h-[400px] md:h-[300px] max-md:mt-8">
                        <img
                            src="https://readymadeui.com/login-image.webp"
                            className="w-full h-full max-md:w-4/5 mx-auto block object-cover"
                            alt="Dining Experience"
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SignUp;
