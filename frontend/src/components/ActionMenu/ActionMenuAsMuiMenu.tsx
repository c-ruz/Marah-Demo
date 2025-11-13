import { Menu, MenuItem } from '@mui/material'
import { ArrowBack } from '@mui/icons-material'
import useActionMenuLogic from './useActionMenuLogic.ts'
import useGameStore from '../../store/GameStateStore.ts'
import type { Action } from '../../types/types.ts'
import type { ActionTree } from './useActionMenuLogic.ts'

interface Props {
  anchorEl: HTMLElement | null
  onClose: () => void
  menuActions: Action[]
}

export default function ActionMenuAsMuiMenu({ anchorEl, onClose, menuActions }: Props) {
  const open = Boolean(anchorEl)
  const { doAction, fetchGameState } = useGameStore()

  const {
    currentMenuLevel,
    setCurrentMenuLevel,
    menuHistory,
    handleBack,
    addToHistory,
    isLoading,
  } = useActionMenuLogic(menuActions)

  const handleMenuItemClick = async (item: ActionTree) => {
    if (item.children && item.children.length > 0) {
      setCurrentMenuLevel(item.children)
      addToHistory()
    }
    else if (item.action) {
      onClose()
      await doAction(item.action)
      await fetchGameState()
    }
  }

  return (
    <Menu
      id="action-tree-menu"
      anchorEl={anchorEl}
      open={open}
      onClose={(event: React.MouseEvent<HTMLLIElement, MouseEvent>, reason) => {
        // When the menu is closed by clicking the backdrop or pressing Escape,
        // stop propagation so the click doesn't reach parent elements (like the Panel Button)
        if (reason === 'backdropClick' || reason === 'escapeKeyDown') {
          // Some events may be SyntheticEvent or native. Try to stop propagation safely.
          try {
            event.stopPropagation()
          }
          catch {
            // ignore
          }
        }
        onClose()
      }}
      slotProps={{
        list: {
          'aria-labelledby': 'action-tree-button',
          'sx': { py: 0, maxHeight: '300px', overflowY: 'auto' },
        },
      }}
    >
      {!isLoading && (
        <>
          {currentMenuLevel.map(item => (
            <MenuItem
              key={item.path}
              onClick={(e) => {
                e.stopPropagation()
                handleMenuItemClick(item)
              }}
            >
              {item.name}
            </MenuItem>
          ))}
          {menuHistory.length > 0 && (
            <MenuItem
              onClick={(e) => {
                e.stopPropagation()
                handleBack()
              }}
            >
              <ArrowBack />
              Back
            </MenuItem>
          )}
        </>
      )}
    </Menu>
  )
}
