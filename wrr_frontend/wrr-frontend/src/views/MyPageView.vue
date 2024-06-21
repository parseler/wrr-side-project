<script setup>
import { logout } from "@/api/auth";
import { useRouter } from "vue-router";

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
    <button
      class="mt-6 select-none rounded-lg bg-gray-900 py-3 px-12 text-center align-middle font-mono text-xl font-bold uppercase text-white shadow-md shadow-gray-900/10 transition-all hover:shadow-lg hover:shadow-gray-900/20 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
      type="button"
      @click="userLogout"
    >
      Log out
    </button>
  </div>
</template>

<style scoped></style>
