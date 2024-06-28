<script setup>
import { getBoxList, getMovementList, saveWod } from "@/api/wod";
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

const movementList = ref([]);
const boxList = ref([]);
const workoutTypeList = ["AMRAP", "OnOff", "RFT", "EMOM"];
const repsUnitOptions = ["rep", "m", "km", "cal"];
const weightUnitOptions = ["kg", "lb"];

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
  const workout = {
    workoutId: 0,
    wodId: 0,
    type: { workoutType: "" },
    seq: 0,
    parentWorkoutId: 0,
    workoutMovements: [],
  };
  wod.value.workouts.push(workout);
};

const deleteWorkout = (index) => {
  wod.value.workouts.splice(index, 1);
};

const addMovement = (index) => {
  const workoutMovement = {
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
  };

  wod.value.workouts[index].workoutMovements.push(workoutMovement);
};

const submitWod = () => {

  for (var i = 0; i < wod.value.workouts.length; i++) {
    wod.value.workouts[i].seq = i + 1;

    for (var j = 0; j < wod.value.workouts[i].workoutMovements.length; j++) {
      wod.value.workouts[i].workoutMovements[j].seq = j + 1;
    }
  }
  saveWod(wod.value,
    (response) => {
      console.log(response);
    },
    (error) => {
      console.log(error);
    }
  )
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
          <input v-model="wod.wodName" type="text" name="wodName" id="wodName" />
        </div>
        <div class="form-group">
          <label for="boxName">Box Name</label>
          <select name="boxName" id="boxName" v-model="wod.boxId">
            <option :value="box.boxId" v-for="(box, index) in boxList" :key="box.boxId">
              {{ box.boxName }}
            </option>
          </select>
        </div>
        <div class="form-group">
          <label for="teamSize">Team of</label>
          <input v-model="wod.teamSize" type="number" name="teamSize" id="teamSize" min="1" />
        </div>
        <div class="form-group">
          <label for="description">Description</label>
          <textarea v-model="wod.description" name="description" id="description"></textarea>
        </div>
        <button type="button" @click="addWorkout" class="submit-button mt-3">
          Add Workout
        </button>
        <!-- workout -->
        <fieldset v-for="(workout, index) in wod.workouts" :key="index">
          <legend>Workout {{ index + 1 }}</legend>
          <div class="form-group">
            <label for="workoutType">Workout Type</label>
            <select name="workoutType" id="workoutType" v-model="workout.type.workoutType">
              <option :value="type" v-for="(type, index) in workoutTypeList" :key="index">{{ type }}</option>
            </select>
          </div>
          <div v-if="workout.type.workoutType === 'EMOM'">
            <div class="form-group">
              <label for="round">Round</label>
              <input v-model="workout.type.round" type="number" name="round" id="round" min="1" />
            </div>
            <div class="form-group">
              <label for="timeCap">Time Cap (HH:MM:SS)</label>
              <input v-model="workout.type.timeCap" type="text" name="timeCap" id="timeCap" placeholder="HH:MM:SS" />
            </div>
          </div>
          <div v-if="workout.type.workoutType === 'RFT'">
            <div class="form-group">
              <label for="round">Round</label>
              <input v-model="workout.type.round" type="number" name="round" id="round" min="1" />
            </div>
            <div class="form-group">
              <label for="timeCap">Time Cap (HH:MM:SS)</label>
              <input v-model="workout.type.timeCap" type="text" name="timeCap" id="timeCap" placeholder="HH:MM:SS" />
            </div>
          </div>
          <div v-if="workout.type.workoutType === 'AMRAP'">
            <div class="form-group">
              <label for="timeCap">Time Cap (HH:MM:SS)</label>
              <input v-model="workout.type.timeCap" type="text" name="timeCap" id="timeCap" placeholder="HH:MM:SS" />
            </div>
          </div>
          <div v-if="workout.type.workoutType === 'OnOff'">
            <div class="form-group">
              <label for="round">Round</label>
              <input v-model="workout.type.round" type="number" name="round" id="round" min="1" />
            </div>
            <div class="form-group">
              <label for="timeCap">Time Cap (HH:MM:SS)</label>
              <input v-model="workout.type.timeCap" type="text" name="timeCap" id="timeCap" placeholder="HH:MM:SS" />
            </div>
            <div class="form-group">
              <label for="onTime">On Time</label>
              <input v-model="workout.type.onTime" type="text" name="onTime" id="onTime" placeholder="HH:MM:SS" />
            </div>
            <div class="form-group">
              <label for="offTime">Off Time</label>
              <input v-model="workout.type.offTime" type="text" name="offTime" id="offTime" placeholder="HH:MM:SS" />
            </div>
          </div>

          <fieldset v-for="(movement, index) in workout.workoutMovements" :key="index">
            <legend>Movement {{ index + 1 }}</legend>
            <div class="form-group">
              <label for="workoutType">Movement Name</label>
              <select name="movementId" id="movementId" v-model="movement.movementId" required>
                <option :value="move.movementId" v-for="(move, index) in movementList" :key="index">{{ move.movementName
                  }}</option>
              </select>
            </div>
            <div class="form-group">
              <label for="repsMale">Reps Male</label>
              <input type="number" v-model="movement.repsMale" step="0.1" name="repsMale" id="repsMale">
              <label for="repsFemale">Reps Female</label>
              <input type="number" v-model="movement.repsFemale" step="0.1" name="repsFemale" id="repsFemale">
              <label for="repsUnit">Reps Unit</label>
              <select name="repsUnit" id="repsUnit" v-model="movement.repsUnit">
                <option :value="unit" v-for="(unit, index) in repsUnitOptions" :key="index">
                  {{ unit }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label for="weightMale">Weight Male</label>
              <input type="number" v-model="movement.weightMale" step="0.1" name="weightMale" id="weightMale">
              <label for="weightFemale">Weight Female</label>
              <input type="number" v-model="movement.weightFemale" step="0.1" name="weightFemale" id="weightFemale">
              <label for="weightUnit">Weight Unit</label>
              <select name="weightUnit" id="weightUnit" v-model="movement.weightUnit">
                <option :value="unit" v-for="(unit, index) in weightUnitOptions" :key="index">
                  {{ unit }}
                </option>
              </select>
            </div>
            <button class="submit-button mt-3" @click="workout.workoutMovements.splice(index, 1)" type="button">Delete
              Movement</button>
          </fieldset>

          <button class="submit-button mt-3" @click="addMovement(index)" type="button">Add Movement</button>
          <button class="submit-button mt-3" @click="deleteWorkout(index)" type="button">Delete Workout</button>
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
