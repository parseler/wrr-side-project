<script setup>
import { ref, onBeforeMount } from "vue";
import { useRouter } from "vue-router";

import { useUserStore } from "@/stores/userStore.js";
import { storeToRefs } from "pinia";

const userStore = useUserStore();
const { loginUser, isLogin } = storeToRefs(userStore);
const { isValidToken } = userStore;

const dialogBackdrop = ref(null);

const openDialog = () => {
  dialogBackdrop.value.classList.remove("pointer-events-none", "opacity-0");
  dialogBackdrop.value.classList.add("pointer-events-auto", "opacity-100");
};

const closeDialog = () => {
  dialogBackdrop.value.classList.remove("pointer-events-auto", "opacity-100");
  dialogBackdrop.value.classList.add("pointer-events-none", "opacity-0");
};

const router = useRouter();

const clickLogin = (provider) => {
  window.location.href = `http://localhost:8080/oauth2/authorization/${provider}`;
};

const guestLogin = () => { };

onBeforeMount(() => {
  isValidToken();
});

</script>
<template>
  <div class="flex items-center justify-center px-6 text-center text-word">
    <div class="mt-10 flex flex-col items-center">
      <img src="/src/assets/logo/WRR.png" alt="WRR Logo" style="width: 40%; height: auto" />
      <div v-if="!isLogin">
        <h1
          class="block antialiased tracking-normal font-mono text-5xl font-bold leading-tight text-blue-gray-900 mt-3 !text-2xl md:text-3xl max-w-xl mx-auto !leading-snug">
          Start Drop-In with Online Crossfit Box !!!
        </h1>
        <button
          class="mt-6 w-64 select-none rounded-lg bg-btn-700 py-3 px-12 text-center align-middle font-mono text-xl font-bold uppercase text-word transition-all hover:bg-btn-500"
          type="button" @click="openDialog">
          Sign in
        </button>
        <p class="cursor-pointer hover:text-logo block antialiased font-mono text-base leading-relaxed text-inherit !mt-4 md:max-w-2xl text-[18px] font-normal text-gray-500 text-decoration-line: underline"
          @click="guestLogin">
          Continue as Guest
        </p>
      </div>
      <div v-else class="flex flex-col items-center">
        <RouterLink
          class="mt-6 w-64 select-none rounded-lg bg-btn-700 py-3 px-12 text-center align-middle font-mono text-xl font-bold uppercase text-word transition-all hover:bg-btn-500"
          :to="{ name: 'random-wod' }">
          Generate WOD
        </RouterLink>
        <RouterLink
          class="mt-6 w-64 select-none rounded-lg bg-btn-700 py-3 px-12 text-center align-middle font-mono text-xl font-bold uppercase text-word transition-all hover:bg-btn-500"
          :to="{ name: 'mypage' }">
          Matching
        </RouterLink>
        <RouterLink
          class="mt-6 w-64 select-none rounded-lg bg-btn-700 py-3 px-12 text-center align-middle font-mono text-xl font-bold uppercase text-word transition-all hover:bg-btn-500"
          :to="{ name: 'mypage' }">
          Mypage
        </RouterLink>
        <RouterLink v-if="loginUser.role === 'ROLE_ADMIN'"
          class="mt-6 w-64 select-none rounded-lg bg-btn-700 py-3 px-12 text-center align-middle font-mono text-xl font-bold uppercase text-word transition-all hover:bg-btn-500"
          :to="{ name: 'admin' }">
          Admin
        </RouterLink>
      </div>


    </div>
    <!-- login modal -->
    <div ref="dialogBackdrop"
      class="pointer-events-none fixed inset-0 z-[999] grid h-screen w-screen place-items-center bg-black bg-opacity-60 opacity-0 backdrop-blur-sm transition-opacity duration-300">
      <div
        class="relative m-4 w-full max-w-md min-w-[300px] rounded-lg bg-white font-mono text-base text-black leading-relaxed text-blue-gray-500 antialiased shadow-2xl">
        <div
          class="flex items-center justify-between p-4 font-mono text-2xl antialiased font-semibold leading-snug shrink-0 text-blue-gray-900">
          <h5 class="block font-mono text-xl antialiased font-semibold leading-snug tracking-normal text-blue-gray-900">
            Social Login
          </h5>
          <button @click="closeDialog"
            class="relative h-8 max-h-[32px] w-8 max-w-[32px] select-none rounded-lg text-center align-middle font-mono text-xs font-medium uppercase text-blue-gray-500 transition-all hover:bg-blue-gray-500/10 active:bg-blue-gray-500/30 disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
            type="button">
            <span class="absolute transform -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2">
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor"
                stroke-width="2" class="w-5 h-5">
                <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12"></path>
              </svg>
            </span>
          </button>
        </div>
        <div class="relative p-4 !px-5 font-mono text-base font-light leading-relaxed text-blue-gray-500 antialiased">
          <div class="mb-6">
            <ul class="flex flex-col gap-1 mt-3 -ml-2">
              <button role="menuitem"
                class="mb-4 flex w-full cursor-pointer select-none items-center justify-center gap-3 rounded-md px-3 !py-4 pt-[9px] pb-2 text-start leading-tight shadow-md outline-none transition-all hover:bg-blue-gray-50 hover:bg-opacity-80 hover:text-blue-gray-900 focus:bg-blue-gray-50 focus:bg-opacity-80 focus:text-blue-gray-900 active:bg-blue-gray-50 active:bg-opacity-80 active:text-blue-gray-900"
                @click="clickLogin(`google`)">
                <img src="/src/assets/social-login/google.svg" alt="google" class="w-6 h-6" />
                <h6
                  class="block font-mono text-base antialiased font-semibold leading-relaxed tracking-normal uppercase text-blue-gray-900">
                  Sign in with Google
                </h6>
              </button>
              <button role="menuitem"
                class="mb-4 flex w-full cursor-pointer select-none items-center justify-center gap-3 rounded-md px-3 !py-4 pt-[9px] pb-2 text-start leading-tight shadow-md outline-none transition-all hover:bg-blue-gray-50 hover:bg-opacity-80 hover:text-blue-gray-900 focus:bg-blue-gray-50 focus:bg-opacity-80 focus:text-blue-gray-900 active:bg-blue-gray-50 active:bg-opacity-80 active:text-blue-gray-900">
                <img src="/src/assets/social-login/discord.svg" alt="discord" class="w-6 h-6 rounded-md" />
                <h6
                  class="block font-mono text-base antialiased font-semibold leading-relaxed tracking-normal uppercase text-blue-gray-900">
                  Sign in with Discord
                </h6>
              </button>
              <button role="menuitem"
                class="mb-1 flex w-full cursor-pointer select-none items-center justify-center gap-3 rounded-md px-3 !py-4 pt-[9px] pb-2 text-start leading-tight shadow-md outline-none transition-all hover:bg-blue-gray-50 hover:bg-opacity-80 hover:text-blue-gray-900 focus:bg-blue-gray-50 focus:bg-opacity-80 focus:text-blue-gray-900 active:bg-blue-gray-50 active:bg-opacity-80 active:text-blue-gray-900">
                <img src="/src/assets/social-login/facebook.svg" alt="facebook" class="w-6 h-6 rounded-md" />
                <h6
                  class="block font-mono text-base antialiased font-semibold leading-relaxed tracking-normal uppercase text-blue-gray-900">
                  Sign in with Facebook
                </h6>
              </button>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped></style>
