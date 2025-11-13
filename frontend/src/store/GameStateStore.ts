import { create } from 'zustand'
import type { Action, Panel, Score } from '../types/types.ts'

export interface GameState {
  gridSize: { x: number, y: number }
  cells: Panel[]
  scores: Score[]
  topBarMessage?: string
  menuActions: Action[]
}

type ActionMessage = { message: string } | { error: string }

interface GameStore {
  gameStateLoading: boolean
  gameState: GameState
  actionMessage: { message: string, severity: 'success' | 'error' }
  showActionMessage: boolean
  fetchGameState: () => Promise<void>
  doAction: (action: Action) => Promise<void>
  toggleActionMessage: () => void
}

const API_URL = 'http://localhost:8080'

const useGameStore = create<GameStore>()(set => ({
  gameStateLoading: false,
  actionsLoading: false,
  gameState: { gridSize: { x: 0, y: 0 }, cells: [], scores: [], menuActions: [] },
  actionMessage: { message: '', severity: 'error' },
  showActionMessage: false,

  fetchGameState: async () => {
    set({ gameStateLoading: true })

    const resGameState = await fetch(`${API_URL}/state`)
    const gameState: GameState = await resGameState.json()

    set({ gameState, gameStateLoading: false })
  },

  doAction: async (action) => {
    const res = await fetch(
      `${API_URL}/actions`,
      {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: action.id,
      },
    )
    const actionMessage: ActionMessage = await res.json()

    if ('error' in actionMessage) {
      set({
        actionMessage: { message: actionMessage.error, severity: 'error' },
        showActionMessage: true,
      })
    }
    else {
      set({
        actionMessage: { message: actionMessage.message, severity: 'success' },
        showActionMessage: true,
      })
    }
  },

  toggleActionMessage: () => {
    set({ showActionMessage: false })
  },
}))

export default useGameStore
