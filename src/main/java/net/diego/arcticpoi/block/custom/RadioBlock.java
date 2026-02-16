package net.diego.arcticpoi.block.custom;

import net.diego.arcticpoi.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.sounds.SoundSource;

public class RadioBlock extends HorizontalDirectionalBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    // Precomputed voxel shapes for each facing
    private static final VoxelShape SHAPE_NORTH = box(2, 0, 2, 14, 10, 14);
    private static final VoxelShape SHAPE_SOUTH = box(2, 0, 2, 14, 10, 14); // mirrored Z
    private static final VoxelShape SHAPE_EAST  = box(2, 0, 2, 14, 10, 14); // mirrored X/Z
    private static final VoxelShape SHAPE_WEST  = box(2, 0, 2, 14, 10, 14); // mirrored X

    public RadioBlock(Properties properties) {
        super(properties);
        // Set default facing
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH));
    }

    // Set block facing on placement
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    // Add facing property
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        builder.add(FACING);
    }

    // Return voxel shape based on facing
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level,
                               BlockPos pos, CollisionContext context) {

        switch (state.getValue(FACING)) {
            case NORTH: return SHAPE_NORTH;
            case SOUTH: return SHAPE_SOUTH;
            case EAST:  return SHAPE_EAST;
            case WEST:  return SHAPE_WEST;
            default:    return SHAPE_NORTH;
        }
    }

    // Play custom sound on right-click
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand,
                                 BlockHitResult hit) {

        if (!level.isClientSide) {
            level.playSound(null, pos,
                    ModSounds.RADIO_SOUND.get(), // your custom sound
                    SoundSource.BLOCKS,
                    1.0F,
                    1.0F);
        }

        return InteractionResult.SUCCESS;
    }
}
