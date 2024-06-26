<script setup>
import { getBoxList, getMovementList } from "@/api/wod";
import { onMounted, ref } from "vue";

const wod = ref({
  wodId: 0,
  boxId: 0,
  wodName: "",
  teamSize: 1,
  likes: 0,
  description: "",
  workouts: [],
});

const workout = ref({
  workoutId: 0,
  wodId: 0,
  type: "",
  seq: 0,
  parentWorkoutId: 0,
  workoutMovements: [],
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
  seq: 0,
});

const movementList = ref([]);
const boxList = ref([]);
const workoutTypeList = ["amrap", "onoff", "rft", "emom"];

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
  );
});

const addWorkout = () => {
  const workout = ref({
    workoutId: 0,
    wodId: 0,
    type: "",
    seq: 0,
    parentWorkoutId: 0,
    workoutMovements: [],
  });
  wod.value.workouts.push({ workout });
};

const deleteWorkout = (index) => {
  wod.value.workouts.splice(index, 1);
};

const submitWod = () => {
  console.log(wod.value);
};
</script>

<template>
  <div class="form-container">
    <form @submit.prevent="submitWod">
      <fieldset>
        <legend>WOD</legend>
        <div class="form-group">
          <label for="wodName">WOD Name</label>
          <input
            v-model="wod.wodName"
            type="text"
            name="wodName"
            id="wodName"
          />
        </div>
        <div class="form-group">
          <label for="boxName">Box Name</label>
          <select name="boxName" id="boxName" v-model="wod.boxId">
            <option
              :value="box.boxId"
              v-for="(box, index) in boxList"
              :key="box.boxId"
            >
              {{ box.boxName }}
            </option>
          </select>
        </div>
        <div class="form-group">
          <label for="teamSize">Team of</label>
          <input
            v-model="wod.teamSize"
            type="number"
            name="teamSize"
            id="teamSize"
          />
        </div>
        <div class="form-group">
          <label for="description">Description</label>
          <textarea
            v-model="wod.description"
            name="description"
            id="description"
          ></textarea>
        </div>
        <button type="button" @click="addWorkout" class="add-workout-button">
          Add Workout
        </button>
        <fieldset v-for="(workout, index) in wod.workouts" :key="index">
          <legend>Workout {{ index + 1 }}</legend>
          <div class="form-group">
            <label for="workoutType">Workout Type</label>
            <select name="workoutType" id="workoutType">
                <option :value="type" v-for="(type, index) in workoutTypeList" :key="index">{{ type }}</option>
            </select>
          </div>
          <button class="submit-button mt-3" @click="deleteWorkout(index)">Delete</button>
        </fieldset>
      </fieldset>
      <button class="submit-button mt-3">Save</button>
    </form>
  </div>
</template>

<style scoped>
.form-container {
  background-color: #015807;
  padding: 20px;
  border-radius: 10px;
  max-width: 600px;
  margin: 0 auto;
  color: #fefae0;
}

fieldset {
  border: 2px solid #fca311;
  border-radius: 10px;
  padding: 20px;
  background-color: #fefae0;
  color: #015807;
}

legend {
  font-size: 1.5rem;
  font-weight: bold;
  color: #fca311;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
  color: #015807;
}

.form-group input,
.form-group select,
.form-group textarea {
  width: 100%;
  padding: 10px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
}

.form-group input:focus,
.form-group select:focus,
.form-group textarea:focus {
  border-color: #fca311;
  outline: none;
  box-shadow: 0 0 5px rgba(252, 163, 17, 0.5);
}

.form-group textarea {
  height: 100px;
  resize: vertical;
}

.submit-button {
  display: block;
  width: 100%;
  background-color: #fca311;
  color: #fefae0;
  padding: 15px;
  font-size: 1rem;
  font-weight: bold;
  text-transform: uppercase;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.submit-button:hover {
  background-color: #ef9b0f;
}
</style>
