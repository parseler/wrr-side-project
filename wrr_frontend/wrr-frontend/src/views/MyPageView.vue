<script setup>
import { logout } from "@/api/auth";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/userStore.js";
import { storeToRefs } from "pinia";

const userStore = useUserStore();
const { loginUser, isLogin } = storeToRefs(userStore);
const { isValidToken } = userStore;

const router = useRouter();

const userLogout = () => {
  logout(
    (response) => {
      localStorage.removeItem("accessToken");
      router.replace({ name: "home" });
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div>
    <div
      class="mt-6 select-none rounded-lg bg-btn-700 py-3 px-12 text-center align-middle font-mono text-xl font-bold uppercase text-word transition-all hover:bg-btn-500"
    >
      안녕, {{ loginUser.name }}!!!
  </div>
    <button
      class="mt-6 w-64 select-none rounded-lg bg-btn-700 py-3 px-12 text-center align-middle font-mono text-xl font-bold uppercase text-word transition-all hover:bg-btn-500"
      type="button"
      @click="userLogout"
    >
      Log out
    </button>
  </div>
</template>

<style scoped></style>
