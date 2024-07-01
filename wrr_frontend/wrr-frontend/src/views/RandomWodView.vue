<script setup>
import { getRandomWod } from '@/api/wod';
import { onMounted, ref } from 'vue';

const wod = ref({});

const getWod = () => {
  getRandomWod(
    (response) => {
      wod.value = response.data;
      console.log(wod.value);
    },
    (error) => {
      console.log(error);
    }
  );
};

onMounted(() => {
  getWod();
})
</script>

<template>
  <!-- component -->
  <div class="mt-12 flex flex-col items-center justify-center">
    <div class="relative flex w-full max-w-[48rem] flex-col rounded-xl bg-back text-logo shadow-md text-center">
      <div class="p-6 flex flex-col items-center justify-center w-full">
        <div class="mb-4 block font-sans text-2xl font-bold leading-snug tracking-normal antialiased">
          {{ wod.wodName }}
        </div>
        <div v-if="wod.teamSize > 1"
          class="mb-1 block font-sans text-2xl font-semibold leading-relaxed antialiased text-center">
          Team of {{ wod.teamSize }}
        </div>
        <div v-for="(workout, index) in wod.workouts" :key="workout.workoutId">
          
        </div>
        <p class="mb-1 block font-sans text-2xl font-semibold leading-relaxed antialiased text-center">
          3 Rounds For Time
        </p>
        <p class="mb-1 block font-sans text-2xl font-semibold leading-relaxed antialiased text-center">
          60 Double Unders
        </p>
        <p class="mb-1 block font-sans text-2xl font-semibold leading-relaxed antialiased text-center">
          40 WallBallShots
        </p>
        <p class="mb-1 block font-sans text-2xl font-semibold leading-relaxed antialiased text-center">
          20 Devil Presses
        </p>
      </div>
    </div>
    <div class="mt-12">
      <button
        class="w-64 select-none rounded-lg bg-btn-700 py-3 px-12 text-center align-middle font-mono text-xl font-bold uppercase text-word transition-all hover:bg-btn-500"
        @click="getWod()">
        Regenerate WOD
      </button>
    </div>
  </div>
</template>

<style scoped></style>
