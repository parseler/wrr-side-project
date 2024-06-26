<script setup>
import { RouterLink, RouterView } from "vue-router";
import TheHeadingNavBar from "./components/layout/TheHeadingNavBar.vue";
import TheFooter from "./components/layout/TheFooter.vue";
</script>

<template>
  <div class="bg-back h-screen flex flex-col">
    <header>
      <TheHeadingNavBar />
    </header>
    <main class="flex-grow">
      <router-view></router-view>
    </main>
    <footer class="footer">
      <TheFooter />
    </footer>
  </div>
</template>

<style scoped>
main {
  flex: 1;
}
.footer {
  width: 100%;
}
</style>
