<script setup>
import { getRandomWod } from '@/api/wod';
import { onMounted, ref } from 'vue';
import { useWodStore } from "@/stores/wodStore";
import { storeToRefs } from "pinia";

const wod = ref({});
const wodStore = useWodStore();
const { movementList, boxList, workoutTypeList } = storeToRefs(wodStore);

const getWod = () => {
  getRandomWod(
    (response) => {
      wod.value = response.data;
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
          <p v-if="workoutTypeList.find(type => type.typeId === workout.workoutType.typeId).typeName === 'RFT'"
            class="mb-1 block font-sans text-2xl font-semibold leading-relaxed antialiased text-center">
            {{ workout.workoutType.round }} Rounds For Time
          </p>
          <p v-if="workoutTypeList.find(type => type.typeId === workout.workoutType.typeId).typeName === 'AMRAP'"
            class="mb-1 block font-sans text-2xl font-semibold leading-relaxed antialiased text-center">
            AMRAP {{ workout.workoutType.timeCap }}
          </p>
          <p v-if="workoutTypeList.find(type => type.typeId === workout.workoutType.typeId).typeName === 'OnOff'"
            class="mb-1 block font-sans text-2xl font-semibold leading-relaxed antialiased text-center">
            {{ workout.workoutType.round }} Rounds
            {{ workout.workoutType.onTime }} On : {{ workout.workoutType.offTime }} Off
          </p>
          <p v-if="workoutTypeList.find(type => type.typeId === workout.workoutType.typeId).typeName === 'EMOM'"
            class="mb-1 block font-sans text-2xl font-semibold leading-relaxed antialiased text-center">
            Every {{ workout.workoutType.timeCap }} x {{ workout.workoutType.round }}
          </p>

          <div v-for="(movement, index) in workout.workoutMovements" :key="movement.workoutMovementId">
            <p class="mb-1 block font-sans text-2xl font-semibold leading-relaxed antialiased text-center">
              {{ movement.repsMale }}
              <span v-if="movement.repsMale !== movement.repsFemale">/{{ movement.repsFemale }}</span>
              <span v-if="movement.repsUnit !== 'rep'">{{ movement.repsUnit }}</span>
              {{ movementList.find(move => move.movementId === movement.movementId).movementName }}
              <span v-if="movement.weightMale !== 0">{{ movement.weightMale }}/{{ movement.weightFemale }} {{
                movement.weightUnit }}</span>
            </p>
          </div>
        </div>
        <div class="mt-6 block font-sans text-2xl text-white font-bold leading-snug tracking-normal antialiased">
          * {{ wod.description }}
        </div>
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
