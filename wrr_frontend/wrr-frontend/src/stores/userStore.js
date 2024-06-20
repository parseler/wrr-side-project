import { isValid, reissue } from "@/api/auth";
import { getUserInfo } from "@/api/user";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore("user", () => {
  const loginUser = ref({});
  const isLogin = ref(false);
  
  const isValidToken = () => {
    let accessToken = localStorage.getItem("accessToken");

    isValid(
      (response) => {
        getUserInfo(
          accessToken,
          (response) => {
            loginUser.value = response.data;
            isLogin.value = true;
          },
          (error) => {
            loginUser.value = null;
            isLogin.value = false;
          }
        );
      },
      (error) => {
        reissue(
          (response) => {
            let accessToken = response.headers.get("accesstoken");
            localStorage.setItem("accessToken", accessToken);

            getUserInfo(
              accessToken,
              (response) => {
                if (response.status === 200) {
                  isLogin.value = true;
                  loginUser.value = response.data;
                }
              },
              (error) => {
                console.log(error);
              }
            );
          },
          (error) => {
            console.log(error);
            isLogin.value = false;
            loginUser.value = null;
            localStorage.removeItem("accessToken");
          }
        );
      }
    );
  };

  return {
    loginUser,
    isLogin,
    isValidToken,
  };
});
