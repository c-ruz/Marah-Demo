import { Box, Button, Grid, Tooltip, tooltipClasses, Typography, Zoom, type TooltipProps } from '@mui/material'
import { useState } from 'react'
import type { PanelEntity } from '../../types/types.ts'
import ActionMenuAsMuiMenu from '../ActionMenu/ActionMenuAsMuiMenu.tsx'
import useGameStore from '../../store/GameStateStore.ts'
import { styled } from '@mui/material/styles'

const API_URL = 'http://localhost:8080'

const EntityTooltip = styled(({ className, ...props }: TooltipProps) => (
  <Tooltip {...props} arrow classes={{ popper: className }} />
))(() => ({
  [`& .${tooltipClasses.tooltip}`]: {
    minWidth: 300,
    fontSize: '1em',
  },
}))

interface EntityDisplayProps {
  entity: PanelEntity
}

function EntityDisplay({ entity }: EntityDisplayProps) {
  const { name, attributes, actions, img } = entity

  const [hasError, setHasError] = useState(false)
  const { doAction, fetchGameState } = useGameStore()

  const imageUrl = `${API_URL}/resources/static/${img ? img : 'default.png'}`

  const textClamp = {
    overflow: 'hidden',
    whiteSpace: 'nowrap',
    textOverflow: 'ellipsis',
  }

  const handleImageError = () => {
    setHasError(true)
  }

  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null)

  const handleClick = async (event: React.MouseEvent<HTMLElement>) => {
    event.stopPropagation()
    if (actions.length == 1) {
      // If there's only one action, perform it directly
      await doAction(actions[0])
      await fetchGameState()
    }
    else {
      setAnchorEl(event.currentTarget)
    }
  }

  const handleClose = () => {
    setAnchorEl(null)
  }

  // Fallback to a default image if the API image fails to load
  const fallbackSrc = hasError ? 'default.png' : imageUrl

  return (
    <Box>
      <EntityTooltip
        title={(
          <Grid container spacing={1}>
            <Grid size={12}>
              <Typography>{name}</Typography>
            </Grid>
            {attributes.map(attr => (
              <>
                <Grid size={5}>
                  <Typography sx={textClamp}>{attr.name}</Typography>
                </Grid>
                <Grid size={1}>
                  <Typography>:</Typography>
                </Grid>
                <Grid size={6}>
                  <Typography>{attr.value}</Typography>
                </Grid>
              </>
            ))}
          </Grid>
        )}
        placement="top"
        slots={{ transition: Zoom }}
      >
        {actions.length ? (
          <Button
            disableRipple
            onClick={handleClick}
          >
            <Box
              component="img"
              src={fallbackSrc}
              alt={entity.name}
              onError={handleImageError}
              sx={{
                width: '200px',
                height: '200px',
                objectFit: 'contain',
              }}
            />
          </Button>
        ) : (
          <Box
            component="img"
            src={fallbackSrc}
            alt={entity.name}
            onError={handleImageError}
            sx={{
              width: '200px',
              height: '200px',
              objectFit: 'contain',
              ":hover": {
                cursor: 'default'
              }
            }}
          />
        )}
      </EntityTooltip>
      <ActionMenuAsMuiMenu
        anchorEl={anchorEl}
        onClose={handleClose}
        menuActions={entity.actions}
      />
    </Box>
  )
}

export default EntityDisplay
