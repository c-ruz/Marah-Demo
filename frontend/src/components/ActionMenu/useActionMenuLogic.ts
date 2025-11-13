import { useEffect, useState } from 'react'
import type { Action } from '../../types/types.ts'

export interface ActionTree {
  name: string
  path: string
  children?: ActionTree[]
  action?: Action
}

export default function useActionMenuLogic(menuActions: Action[]) {
  const [currentMenuLevel, setCurrentMenuLevel] = useState<ActionTree[]>([])
  const [menuHistory, setMenuHistory] = useState<ActionTree[][]>([])
  const [isLoading, setIsLoading] = useState<boolean>(true)

  useEffect(() => {
    setIsLoading(true)
    const root: ActionTree = { name: 'root', path: '', children: [] }

    menuActions.forEach((action) => {
      const parts = action.name.split('/')
      let currentLevel = root.children
      let currentPath = ''

      parts.forEach((part, index) => {
        currentPath = currentPath === '' ? part : `${currentPath}/${part}`
        let existingNode = currentLevel?.find(node => node.name === part)

        if (!existingNode) {
          existingNode = { name: part, path: currentPath }
          if (index === parts.length - 1) {
            // This is a leaf node (an actual action)
            existingNode.action = action
          }
          else {
            // This is a group node
            existingNode.children = []
          }
          currentLevel?.push(existingNode)
        }

        // Move to the next level
        currentLevel = existingNode.children
      })
    })

    setCurrentMenuLevel(root.children || [])
    setMenuHistory([])
    setIsLoading(false)
  }, [menuActions])

  const handleBack = () => {
    if (menuHistory.length > 0) {
      const previousLevel = menuHistory[menuHistory.length - 1]
      setMenuHistory(prevHistory => prevHistory.slice(0, prevHistory.length - 1))
      setCurrentMenuLevel(previousLevel)
    }
  }

  const addToHistory = () => {
    setMenuHistory(prevHistory => [...prevHistory, currentMenuLevel])
  }

  return {
    currentMenuLevel,
    setCurrentMenuLevel,
    menuHistory,
    handleBack,
    addToHistory,
    isLoading,
  }
}
