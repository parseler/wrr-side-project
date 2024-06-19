<script setup>
import { ref, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import { reissue } from "@/api/auth.js"
import { getUserInfo } from "@/api/user.js";

import { useUserStore } from "@/stores/userStore.js";
import { storeToRefs } from "pinia";

const userStore = useUserStore();
const { loginUser, isLogin } = storeToRefs(userStore);
const router = useRouter();

onBeforeMount(() => {
  reissue((response) => {
    console.log(response);
    if (response) {
      const accessToken = response.headers.get("accesstoken");

      if (accessToken) {
        localStorage.setItem("accessToken", accessToken);
        getUserInfo(accessToken,
          (response) => {
            if (response.status === 200) {
              isLogin.value = true;
              loginUser.value = response.data;
            }
          }, (error) => {
            console.log(error);
          }
        )

      } else {
        console.log("invalid accessToken");
      }
    }
  }, (error) => {
    console.log(error);
  });
});

router.replace({name: 'home'});
</script>

<template>
    <div>
        
    </div>
</template>

<style scoped>

</style>