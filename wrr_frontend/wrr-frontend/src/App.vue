<script setup>
import { RouterLink, RouterView } from "vue-router";
import TheHeadingNavBar from "./components/layout/TheHeadingNavBar.vue";
import TheFooter from "./components/layout/TheFooter.vue";
</script>

<template>
  <div class="bg-back h-screen">
    <header>
      <TheHeadingNavBar />
    </header>
    <main>
      <div>
        <router-view></router-view>
      </div>
    </main>
    <footer class="footer">
      <TheFooter />
    </footer>
  </div>
</template>

<style>
.footer {
  width: 100%;
  height: 95px;
  bottom: 0;
  position: fixed;
}
</style>
