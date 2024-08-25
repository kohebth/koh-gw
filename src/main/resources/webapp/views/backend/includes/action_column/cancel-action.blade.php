@can('edit_' . str_replace('-', '_', $moduleName))
    @if(!$item->isCancel())
        <button wire:click="updateMultiple('cancel', '{{ $item->id }}')"
                class="btn btn-danger btn-sm mb-1" data-toggle="tooltip" title="{{__("Cancel")}}">
            <i class="fas fa-ban"></i>
        </button>
    @endif
@endcan
