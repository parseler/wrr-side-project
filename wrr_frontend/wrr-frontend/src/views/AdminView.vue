<script setup>
import { getBoxList, getMovementList } from '@/api/wod';
import { onMounted, ref } from 'vue';

const wod = ref({
    wodId: 0,
    boxId: 0,
    wodName: "",
    teamSize: 1,
    likes: 0,
    description: "",
    workouts: []
});

const workout = ref({
    workoutId: 0,
    wodId: 0,
    type: "",
    seq: 0,
    parentWorkoutId: 0,
    workoutMovements: []
});

const workoutMovement = ref({
    workoutMovementId: 0,
    workoutId: 0,
    movementId: 1,
    repsUnit: "",
    repsMale: 0,
    repsFemale: 0,
    weightUnit: "",
    weightMale: 0,
    weightFemale: 0,
    synchronized: 0,
    seq: 0
});

const movementList = ref([]);
const boxList = ref([]);

onMounted(async () => {
    getMovementList(
        (response) => {
            movementList.value = response.data;
        },
        (error) => {
            console.log(error);
        }
    );
    getBoxList(
        (response) => {
            boxList.value = response.data;
        },
        (error) => {
            console.log(error);
        }
    )
});

const submitWod = () => {
    console.log(wod.value);
};
</script>

<template>
    <div>
        <form @submit.prevent="submitWod">
            <fieldset>
                <legend>WOD</legend>
                <div>
                    <label for="wodName">WOD Name</label>
                    <input v-model="wod.wodName" type="text" name="wodName" id="wodName" />
                </div>
                <div>
                    <label for="boxName">Box Name</label>
                    <select name="boxName" id="boxName" v-model="wod.boxId">
                        <option :value="box.boxId" v-for="(box, index) in boxList" :key="box.boxId">{{ box.boxName }}
                        </option>
                    </select>
                </div>
                <div>
                    <label for="teamSize">Team of</label>
                    <input v-model="wod.teamSize" type="number" name="teamSize" id="teamSize" />
                </div>
                <div>
                    <label for="description">Description</label>
                    <textarea v-model="wod.description" type="text"></textarea>
                </div>

            </fieldset>
            <button
                class="mt-6 w-64 select-none rounded-lg bg-btn-700 py-3 px-12 text-center align-middle font-mono text-xl font-bold uppercase text-word transition-all hover:bg-btn-500">
                save
            </button>
        </form>
    </div>
</template>

<style scoped></style>