<script setup>
import { RouterLink, RouterView } from "vue-router";
import TheHeadingNavBar from "./components/layout/TheHeadingNavBar.vue";
import TheFooter from "./components/layout/TheFooter.vue";
</script>

<template>
  <header>
      <TheHeadingNavBar />
  </header>
  <main>
    <div class="bg-back h-full">
      <router-view></router-view>
    </div>
  </main>
  <footer class="footer">
      <TheFooter />
  </footer>
</template>

<style>
.footer {
  width: 100%;
  height: 10%;
}
</style>
