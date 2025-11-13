import { ListSubheader, Menu, MenuItem } from '@mui/material'
import type { Action } from '../../types/types.ts'
import { styled } from '@mui/material/styles'
import useGameStore from '../../store/GameStateStore.ts'

const StyledListHeader = styled(ListSubheader)({
  backgroundImage: 'var(--Paper-overlay)',
})

interface GroupedActionMenuProps {
  actions: { action: Action, category: string }[]
  anchorEl: HTMLElement | null
  onClose: () => void
}

function GroupedActionMenu({ actions, anchorEl, onClose }: GroupedActionMenuProps) {
  const { doAction, fetchGameState } = useGameStore()
  const open = Boolean(anchorEl)

  const groupedActions = actions.reduce((groups, item) => {
    const category = item.category || 'Uncategorized'
    if (!groups[category]) {
      groups[category] = []
    }
    groups[category].push(item.action)
    return groups
  }, {} as Record<string, Action[]>)

  return (
    <Menu
      id="grouped-menu"
      anchorEl={anchorEl}
      open={open}
      onClose={onClose}
      slotProps={{
        list: {
          'aria-labelledby': 'basic-button',
          'sx': {
            py: 0,
            maxHeight: '300px',
            overflowY: 'auto',
          },
        },
      }}
    >
      {Object.entries(groupedActions).map(([category, actions]) => (
        <div key={category}>
          <StyledListHeader>{category}</StyledListHeader>
          {actions.map(action => (
            <MenuItem
              key={action.name}
              onClick={() => {
                onClose()
                doAction(action)
                  .then(() => fetchGameState())
              }}
            >
              {action.name}
            </MenuItem>
          ))}
        </div>
      ))}
    </Menu>
  )
}

export default GroupedActionMenu
