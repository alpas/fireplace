<template>
    <div>
        <ul class="task-list max-w-4xl">
            <li class="py-1" v-for="task in tasks" :key="task.id"
                :class="{completed: task.completed, editing: task === editedTask}">
                <div class="view flex items-center">
                    <input type="checkbox" class="form-checkbox text-green-500 h-6 w-6" v-model="task.completed"
                           @change="updateTaskStatus(task)">
                    <label class="text-lg font-light mx-6 flex-1 py-2 text-gray-700" @dblclick="editTask(task)">
                        {{ task.body }}
                    </label>
                    <button class="destroy text-red-500 hover:text-red-700 hidden w-10 text-2xl"
                            @click="removeTask(task)"></button>
                </div>
                <input type="text"
                       class="edit appearance-none bg-gray-300 border border-gray-300 border-white focus:outline-none leading-tight px-3 py-3 rounded text-gray-700 text-lg w-fulll"
                       v-model="task.body"
                       v-task-focus="task === editedTask"
                       @keyup.enter="doneEdit(task)"
                       @blur="doneEdit(task)"
                       @keyup.esc="cancelEdit(task)">
            </li>
        </ul>

        <div class="mt-10 max-w-5xl">
            <form @submit.prevent="createTask" :class="{hidden: editedTask !== null}">
                <input type="text" placeholder="Add a new task" v-model="form.body" :disabled="form.isWorking"
                       ref="newTaskInput"
                       class="appearance-none bg-gray-300 border border-white focus:outline-none px-3 py-3 rounded text-gray-700 text-lg w-full">
            </form>
        </div>
    </div>
</template>


<script>
    import Form from "../Form";

    export default {
        props: {
            initialTasks: {type: Array, required: false, default: () => []},
            projectId: {type: Number, required: true}
        },
        data() {
            return {
                form: new Form({body: '', completed: false}),
                tasks: this.initialTasks || [],
                editedTask: null,
                beforeEditCache: null
            }
        },
        directives: {
            'task-focus': (el, binding) => {
                if (binding.value) {
                    el.focus()
                }
            }
        },
        methods: {
            async createTask() {
                const {data} = await this.form.post(`/projects/${this.projectId}/tasks`)
                this.form.reset()
                this.tasks.push(data)
                this.$nextTick(() => {
                    this.$refs.newTaskInput.focus()
                })
            },
            editTask(task) {
                if (task.completed) {
                    return
                }
                this.beforeEditCache = task.body
                this.editedTask = task
            },
            async removeTask(task) {
                await this.form.delete(`/projects/${this.projectId}/tasks/${task.id}`)
                this.tasks = this.tasks.filter(t => t.id !== task.id)
            },
            async updateTaskStatus(task) {
                this.form.body = task.body = task.body.trim()
                this.form.completed = task.completed
                await this.form.patch(`/projects/${this.projectId}/tasks/${task.id}`)
                this.form.reset()
            },
            async doneEdit(task) {
                if (!this.editedTask) {
                    return
                }
                this.editedTask = null
                this.form.body = task.body = task.body.trim()
                if (!task.body) {
                    await this.removeTask(task)
                } else {
                    await this.form.patch(`/projects/${this.projectId}/tasks/${task.id}`)
                    this.form.reset()
                }
            },
            cancelEdit(task) {
                this.editedTask = null
                task.body = this.beforeEditCache
            }
        }
    }
</script>

<style scoped lang="less">
    .task-list {
        li .destroy::after {
            content: '×';
        }

        li:hover .destroy {
            display: block;
        }

        li.completed label {
        @apply text-gray-500 line-through;
        }

        li .edit {
            display: none;
        }

        li.editing .view {
            display: none;
        }

        li.editing .edit {
            display: block;
            margin: 0 0 0 43px;
        }
    }
</style>
