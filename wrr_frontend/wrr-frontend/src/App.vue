<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>

    <div class="wrapper">
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <a href="http://localhost:8080/oauth2/authorization/google">Login with Google</a>
      </nav>
    </div>
  </header>

  <RouterView />
</template>

<style scoped>
</style>
