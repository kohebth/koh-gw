@can('edit_' . str_replace('-', '_', $moduleName))
    @if($admin->isActive())
        <button wire:click="updateMultiple('inactive', '{{$admin->operator_id}}')"
                class="btn btn-warning btn-sm mt-1" data-toggle="tooltip" title="{{__("Inactive")}}">
            <i class="fas fa-ban"></i>
            Inactive
        </button>
    @else
        <button wire:click="updateMultiple('active', '{{$admin->operator_id}}')"
                class="btn btn-success btn-sm mt-1" data-toggle="tooltip" title="{{__("Active")}}">
            <i class="fas fa-check"></i>
            Active
        </button>
    @endif
@endcan
