<script setup>
import { ref } from "vue";

import { useUserStore } from "@/stores/userStore.js";
import { storeToRefs } from "pinia";

const userStore = useUserStore();
const { loginUser, isLogin } = storeToRefs(userStore);

const isShown = ref(false);
</script>

<template>
  <nav
    class="block w-full full-width max-w-screen px-6 py-6 mx-auto text-white bg-back border shadow-md border-back/100 backdrop-blur-2xl backdrop-saturate-200">
    <div class="flex items-center justify-between text-blue-gray-900" style="height: 45px">
      <RouterLink :to="{ name: 'home' }"
        class="flex items-center mr-4 cursor-pointer py-1.5 font-mono text-xl font-semibold leading-relaxed tracking-normal text-inherit antialiased">
        <img src="/src/assets/logo/WRR.png" alt="WRR Logo" style="width: 45px; height: auto" class="mr-2" />
      </RouterLink>

      <div class="flex items-center gap-6">
        <ul class="flex gap-2 my-2 lg:mb-0 lg:mt-0 lg:flex-row lg:items-center lg:gap-6" v-show="isShown">
          <li class="block p-1 font-mono antialiased font-medium leading-normal text-blue-gray-900">
            <a href="#" class="flex items-center transition-colors hover:text-logo-500" v-if="isLogin">
              {{ loginUser.name }}
            </a>
            <a href="#" class="flex items-center transition-colors hover:text-logo-500" v-else>
              Guest
            </a>
          </li>
          <li class="block p-1 font-mono antialiased font-medium leading-normal text-blue-gray-900">
            <a href="#" class="flex items-center transition-colors hover:text-logo-500">
              Matching
            </a>
          </li>
          <li class="block p-1 font-mono antialiased font-medium leading-normal text-blue-gray-900">
            <a href="#" class="flex items-center transition-colors hover:text-logo-500">
              WOD
            </a>
          </li>
        </ul>
        <button
          class="relative h-6 max-h-[40px] w-6 max-w-[40px] select-none rounded-lg text-center align-middle font-mono text-xs font-medium uppercase text-inherit transition-all hover:bg-transparent focus:bg-transparent active:bg-transparent disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
          type="button" @click="isShown = !isShown">
          <span class="absolute transform -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2">
            <svg v-if="isShown" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="3"
              stroke="currentColor" aria-hidden="true" class="w-6 h-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12"></path>
            </svg>
            <svg v-else xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2"
              stroke="currentColor" aria-hidden="true" class="w-6 h-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5">
              </path>
            </svg>
          </span>
        </button>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>
