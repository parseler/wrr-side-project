<script setup>
import { RouterLink, RouterView } from 'vue-router'
import TheHeadingNavBar from './components/layout/TheHeadingNavBar.vue';
import TheFooter from './components/layout/TheFooter.vue'
</script>

<template>
  <header>
    <div>
      <TheHeadingNavBar/>
    </div>
  </header>
  <main>
    <div>
      <router-view></router-view>
    </div>
  </main>
  <footer>
    <div>
      <TheFooter/>
    </div>
  </footer>
</template>

<style scoped>
</style>
