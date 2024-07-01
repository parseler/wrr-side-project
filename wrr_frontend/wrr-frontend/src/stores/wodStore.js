import { defineStore } from "pinia";
import { ref, onMounted } from "vue";
import { getBoxList, getMovementList, getWorkoutTypeList } from "@/api/wod";

export const useWodStore = defineStore("wod", () => {
    const movementList = ref([]);
    const boxList = ref([]);
    const workoutTypeList = ref([]);

    const fetchMovementList = () => {
        getMovementList(
            (response) => {
                movementList.value = response.data;
            },
            (error) => {
                console.log(error);
            }
        );
    };

    const fetchBoxList = () => {
        getBoxList(
            (response) => {
                boxList.value = response.data;
            },
            (error) => {
                console.log(error);
            }
        );
    };

    const fetchWorkoutTypeList = () => {
        getWorkoutTypeList(
            (response) => {
                workoutTypeList.value = response.data;
            },
            (error) => {
                console.log(error);
            }
        );
    };

    onMounted(() => {
        fetchMovementList();
        fetchBoxList();
        fetchWorkoutTypeList();
    });

    return {
        movementList,
        boxList,
        workoutTypeList,
        fetchMovementList,
        fetchBoxList,
        fetchWorkoutTypeList,
    };
});
