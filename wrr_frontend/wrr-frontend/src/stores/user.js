import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore('user', () => {
    
    const loginUser = ref({});
    const isLogin = ref(false);

    return {
        loginUser,
        isLogin,
    }
})