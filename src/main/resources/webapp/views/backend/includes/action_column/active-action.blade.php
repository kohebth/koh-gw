@can('edit_' . str_replace('-', '_', $moduleName))
    @if($item->isActive())
    <button wire:click="updateMultiple('inactive', '{{$item->id}}')"
            class="btn btn-warning btn-sm mb-1" data-toggle="tooltip" title="{{__("Inactive")}}">
        <i class="fas fa-ban"></i>
        Inactive
    </button>
    @else
    <button wire:click="updateMultiple('active', '{{$item->id}}')"
            class="btn btn-success btn-sm mb-1" data-toggle="tooltip" title="{{__("Active")}}">
        <i class="fas fa-check"></i>
        Active
    </button>
    @endif
@endcan
