import { Box, List, ListItemButton, ListItemText, Skeleton } from '@mui/material'
import { ArrowBack } from '@mui/icons-material'
import useGameStore from '../../store/GameStateStore.ts'
import useActionMenuLogic from './useActionMenuLogic.ts'
import type { ActionTree } from './useActionMenuLogic.ts'

interface ActionMenuItemProps {
  item: ActionTree
  setActionMenu: (am: ActionTree[]) => void
  setHistory: () => void
}

function ActionMenuItem({ item, setActionMenu, setHistory }: ActionMenuItemProps) {
  const { doAction, fetchGameState } = useGameStore()

  const handleClick = async () => {
    if (item.children && item.children.length > 0) {
      setActionMenu(item.children)
      setHistory()
    }
    else if (item.action) {
      await doAction(item.action)
      await fetchGameState()
    }
  }

  return (
    <ListItemButton onClick={handleClick}>
      <ListItemText primary={item.name} />
    </ListItemButton>
  )
}

function ActionMenu() {
  const { gameState: { menuActions }, gameStateLoading } = useGameStore()

  const {
    currentMenuLevel,
    setCurrentMenuLevel,
    menuHistory,
    handleBack,
    addToHistory,
    isLoading,
  } = useActionMenuLogic(menuActions)

  if (isLoading || gameStateLoading) {
    return (
      <Skeleton width="50%" height="100%" variant="rounded" />
    )
  }

  return (
    <Box width="50%" overflow="auto">
      <List component="nav">
        {currentMenuLevel.map(item => (
          <ActionMenuItem
            key={item.path}
            item={item}
            setActionMenu={setCurrentMenuLevel}
            setHistory={addToHistory}
          />
        ))}
        {menuHistory.length > 0 && (
          <ListItemButton onClick={handleBack}>
            <ArrowBack />
            <ListItemText primary="Back" />
          </ListItemButton>
        )}
      </List>
    </Box>
  )
}

export default ActionMenu
