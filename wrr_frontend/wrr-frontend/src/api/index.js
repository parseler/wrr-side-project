import axios from 'axios';

// axios 인스턴스 생성
const api = axios.create({
    baseURL: import.meta.env.VITE_APP_API_URL,
    withCredentials: true, // 쿠키를 포함하여 요청
});

// 요청 인터셉터 설정
api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("accessToken");
        if (token) {
            config.headers['accessToken'] = token;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default api;
