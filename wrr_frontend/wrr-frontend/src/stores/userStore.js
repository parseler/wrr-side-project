import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore('user', () => {

    const loginUser = ref({});
    const isLogin = ref(false);
    const isValidToken = () => {
        let accessToken = localStorage.getItem("accessToken");

        
    }

    return {
        loginUser,
        isLogin,
        isValidToken,
    }
})